package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Detail.MeetingCommentAdapter;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.ApplicantVO;
import com.depromeet.hanriver.hanrivermeetup.service.CommunicationService;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.depromeet.hanriver.hanrivermeetup.service.GuestService;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MeetingDetailFragment extends DialogFragment {

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    MeetingDetailViewModel meetingDetailViewModel;
    MeetingCommentViewModel meetingCommentViewModel;
    SwipeMenuListView rv;
    Button comment_btn, join_btn;
    EditText comment_text;
    ImageView profile_img;
    ImageButton back_btn, modify_btn;
    ScrollView scroll;
    TextView room_title, profile_name, detail_info, detail_location, detail_content, joinbtn_border;
    int meeting_seq;
    String room_master_name;
    MeetingDetailFragment self;
    RelativeLayout rl;
    MeetingDetail meetingDetail;


    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;

        getDialog().getWindow().setWindowAnimations(
                R.style.dialog_animation_move);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meetingDetailViewModel = getViewModel();
        meetingCommentViewModel = getCommentViewModel();
        self = this;
    }

    public static MeetingDetailFragment newInstance(int meeting_seq) {
        Bundle args = new Bundle();
        MeetingDetailFragment fragment = new MeetingDetailFragment();
        fragment.meeting_seq = meeting_seq;
        fragment.setArguments(args);
        return fragment;
    }


    private void setupViews(View v) {

        modify_btn = v.findViewById(R.id.detail_room_modify_btn);
        joinbtn_border = v.findViewById(R.id.border_join_Btn);
        scroll = v.findViewById(R.id.detail_scroll);
        scroll.setOverScrollMode(View.OVER_SCROLL_NEVER);
        back_btn = v.findViewById(R.id.detail_back_btn);
        back_btn.setOnClickListener(back_click);
        rv = v.findViewById(R.id.detail_comment_lv);
        profile_img = v.findViewById(R.id.detail_profile_img);
        room_title = v.findViewById(R.id.detail_room_title);
        profile_name = v.findViewById(R.id.detail_name);
        detail_info = v.findViewById(R.id.detail_info);
        detail_location = v.findViewById(R.id.detail_location);
        detail_content = v.findViewById(R.id.detail_content);
        join_btn = v.findViewById(R.id.detail_join_btn);
        comment_btn = v.findViewById(R.id.detail_comment_btn);
        comment_text = v.findViewById(R.id.detail_comment_edit);

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeetingJoinFragment dialog = MeetingJoinFragment.newInstance(meeting_seq, room_master_name, self);
                dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
                dialog.show(getFragmentManager(), "meeting_join");
            }
        });

        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment comment = new Comment();
                comment.setText(comment_text.getText().toString());
                comment.setMeeting_seq(meeting_seq);
                comment.setCreatedTime(getCurrentTime());
                comment.setUserID(PreferencesManager.getUserID());

                mCompositeDisposable.add(CommunicationService.getInstance().addComment(comment)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(self::successAddComment));
                comment_text.setText("");
            }
        });

        rl = v.findViewById(R.id.detail_rl);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeetingModifyRoom dialog = MeetingModifyRoom.newInstance(meetingDetail, self);
                dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
                dialog.setTargetFragment(MeetingDetailFragment.this, 0);
                dialog.show(getFragmentManager(), "modify_meeting");
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#2186F8")));
                deleteItem.setWidth(400);
                deleteItem.setIcon(R.drawable.ic_negative_icon);
                menu.addMenuItem(deleteItem);
            }
        };
        rv.setMenuCreator(creator);

        rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bind();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meeting_detail, container, false);
        setupViews(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        bind();
    }

    @Override
    public void onPause() {
        super.onPause();
        unBind();
    }

    private void bind() {
        mCompositeDisposable = new CompositeDisposable();

//        mCompositeDisposable.add(meetingDetailViewModel.getMeetingDetail(meeting_seq)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::setMeetingDetail));
        mCompositeDisposable.add(HostService.getInstance().getMeetingDetail(meeting_seq)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setMeetingDetail));

//        mCompositeDisposable.add(meetingCommentViewModel.getComments(meeting_seq)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::setComments));
        mCompositeDisposable.add(CommunicationService.getInstance().getComments(meeting_seq)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setComments));

        mCompositeDisposable.add(HostService.getInstance().getMeetingApplicants(meeting_seq)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getApplicationSeq));

    }


    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void getApplicationSeq(@NonNull final List<ApplicantVO> applicantVOs) {
        for (int i = 0; i < applicantVOs.size(); i++) {
            if (applicantVOs.get(i).getUserId().equals(PreferencesManager.getUserID())) {
                join_btn.setBackgroundColor(Color.parseColor("#aaaaaa"));
                join_btn.setEnabled(false);
                join_btn.setText("이미 참여한 모임입니다");
                join_btn.setTextColor(Color.parseColor("#ffffff"));
                joinbtn_border.setBackgroundColor(Color.parseColor("#dcdcdc"));

            }
        }
    }

    private void setMeetingDetail(@NonNull final MeetingDetail meetingDetail) {
        this.meetingDetail = meetingDetail;
        room_master_name = meetingDetail.getNickname();
//        gridview.setAdapter(new GridAdapter(getActivity(),activites));
        room_title.setText("" + meetingDetail.getTitle());
        profile_name.setText("" + meetingDetail.getNickname());
        String time = meetingDetail.getMeeting_time();
        time = time.substring(11, 16);
        detail_info.setText(Html.fromHtml("시간 " + "<b>" + time + "</b>" + " / 인원 " + "<b>" + String.valueOf(meetingDetail.getParticipants_cnt())
                + "명</b> / 회비 " + "<b>" + String.valueOf(meetingDetail.getExpected_cost()) + "</b>" + "원"));
        detail_location.setText(meetingDetail.getMeeting_location() + "");
        detail_content.setText(meetingDetail.getDescription() + "");
        Picasso.get().load(FacebookService.getInstance().getProfileURL(meetingDetail.getUser_id()))
                .transform(CircleTransform.getInstance()).into(profile_img);

        if (meetingDetail.getUser_id().equals(PreferencesManager.getUserID())) {
//            join_btn.setBackgroundColor(Color.parseColor("#aaaaaa"));
//            join_btn.setEnabled(false);
//            join_btn.setText("내가 만든 방입니다");
//            join_btn.setTextColor(Color.parseColor("#ffffff"));
//            joinbtn_border.setBackgroundColor(Color.parseColor("#dcdcdc"));
            join_btn.setVisibility(View.GONE);
            joinbtn_border.setVisibility(View.GONE);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) join_btn.getLayoutParams();
            lp.setMargins(0, 0, 0, 0);
            join_btn.setLayoutParams(lp);
            modify_btn.setVisibility(View.VISIBLE);
        }
        // TestFrag frag = new TestFrag();
//        FragmentManager fragmentManager = getFragmentManager();


//        mActivitesView.setText("한강에서\n" +
//                "원하는 모임을 선택하세요");
    }

    private void setComments(@NonNull final List<Comment> comments) {

        rv.setOnMenuItemClickListener((position, menu, index) -> {
            if (PreferencesManager.getUserID().equals(comments.get(position).getUserID())) {
                switch (index) {
                    case 0:
                        mCompositeDisposable.add(CommunicationService.getInstance().deleteComment(comments.get(position).getId())
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<Boolean>() {
                                    @Override
                                    public void onNext(Boolean bool) {
                                        if (bool == false) {
                                            Toast.makeText(getContext(), "삭제 권한이 없는 댓글입니다. ", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(getContext(), "서버 에러", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {
                                        Toast.makeText(getContext(), "댓글 삭제 완료", Toast.LENGTH_SHORT).show();
                                        bind();
                                    }
                                }));
                        break;
                }
            }
            else{
                Toast.makeText(getContext(), "본인의 댓글만 삭제 가능합니다.", Toast.LENGTH_SHORT).show();
            }
            return false;

        });

        if (comments.toString() == "[]")
            rv.setMinimumHeight(358);
        else {
            rv.setAdapter(new MeetingCommentAdapter(comments, getContext(), this));
            setListViewHeightBasedOnChildren(rv);
        }

    }

    @NonNull
    private MeetingDetailViewModel getViewModel() {
        return ((HanRiverMeetupApplication) getActivity().getApplicationContext()).getMeetingDetailViewModel();
    }

    @NonNull
    private MeetingCommentViewModel getCommentViewModel() {
        return ((HanRiverMeetupApplication) getActivity().getApplicationContext()).getCommentViewModel();
    }

    public String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdfNow.format(date);

        return formatDate;
    }

    private void successAddComment(Comment comment) {
        bind();
    }

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            self.dismiss();
        }
    };

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        getTargetFragment().onActivityResult(0, 0, null);
    }

    public static void setListViewHeightBasedOnChildren(SwipeMenuListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);

        listView.requestLayout();
    }
}

package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
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

import javax.net.ssl.HttpsURLConnection;

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
    SwipeMenuListView rv;
    Button comment_btn, join_btn;
    EditText comment_text;
    ImageView profile_img;
    ImageButton back_btn, modify_btn;
    ScrollView scroll;
    TextView room_title, profile_name, detail_location, detail_content, detail_date, detail_fee, detail_num;
    int meeting_seq;
    String room_master_name;
    MeetingDetailFragment self;
    RelativeLayout rl,detail_top;
    MeetingDetail meetingDetail;
    RelativeLayout emptyCommentview;


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
        detail_top = v.findViewById(R.id.detail_top);
        modify_btn = v.findViewById(R.id.detail_room_modify_btn);
        scroll = v.findViewById(R.id.detail_scroll);
        scroll.setOverScrollMode(View.OVER_SCROLL_NEVER);
        back_btn = v.findViewById(R.id.detail_back_btn);
        back_btn.setOnClickListener(back_click);
        rv = v.findViewById(R.id.detail_comment_lv);
        profile_img = v.findViewById(R.id.detail_profile_img);
        room_title = v.findViewById(R.id.detail_room_title);
        profile_name = v.findViewById(R.id.detail_name);
        detail_date = v.findViewById(R.id.detail_date);
        detail_fee = v.findViewById(R.id.detail_fee);
        detail_num = v.findViewById(R.id.detail_num);
        detail_location = v.findViewById(R.id.detail_location);
        detail_content = v.findViewById(R.id.detail_content);
        join_btn = v.findViewById(R.id.detail_join_btn);
        comment_btn = v.findViewById(R.id.detail_comment_btn);
        comment_text = v.findViewById(R.id.detail_comment_edit);
        emptyCommentview = v.findViewById(R.id.comment_null_rl);

        gradationOnListTop(detail_top);

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeetingJoinFragment dialog = MeetingJoinFragment.newInstance(meeting_seq, room_master_name, self);
                dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
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
                        .doOnNext(res->{
                            if(res.code() == HttpsURLConnection.HTTP_OK){
                                successAddComment(res.body());
                            }
                            else{
                                Toast.makeText(getContext(), "댓글 작성에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .subscribe());
                comment_text.setText(null);
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
                dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
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
        rv.setDividerHeight(0);
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
                .doOnNext(res -> {
                    if (res.code() == HttpsURLConnection.HTTP_OK) {
                        setMeetingDetail(res.body());
                    } else {
                        Toast.makeText(getContext(), "모임 세부정보를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe());

//        mCompositeDisposable.add(meetingCommentViewModel.getComments(meeting_seq)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::setComments));
        mCompositeDisposable.add(CommunicationService.getInstance().getComments(meeting_seq)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(res -> {
                    if (res.code() == HttpsURLConnection.HTTP_OK) {
                        setComments(res.body());
                    } else {
                        Toast.makeText(getActivity(),
                                "댓글을 불러오지 못했습니다. 새로고침을 해주세요.", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe());

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
        time = time.substring(5,7)+"."+time.substring(8,10)+"."+time.substring(11, 16);
        detail_date.setText(time);
        detail_num.setText(String.valueOf(meetingDetail.getParticipants_cnt())+"명");
        detail_fee.setText(String.valueOf(meetingDetail.getExpected_cost())+"원");
        detail_location.setText(meetingDetail.getMeeting_location() + "");
        detail_content.setText(meetingDetail.getDescription() + "");
        Picasso.get().load(FacebookService.getInstance().getProfileURL(meetingDetail.getUser_id()))
                .transform(CircleTransform.getInstance()).into(profile_img);

        if (meetingDetail.getUser_id().equals(PreferencesManager.getUserID())) {
            join_btn.setVisibility(View.GONE);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) join_btn.getLayoutParams();
            lp.setMargins(0, 0, 0, 0);
            join_btn.setLayoutParams(lp);
            modify_btn.setVisibility(View.VISIBLE);
        }
    }

    private void setComments(@NonNull final List<Comment> comments) {

        rv.setOnMenuItemClickListener((position, menu, index) -> {
            if (PreferencesManager.getUserID().equals(comments.get(position).getUserID())) {
                switch (index) {
                    case 0:
                        mCompositeDisposable.add(CommunicationService.getInstance().deleteComment(comments.get(position).getId())
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnNext(res->{
                                    if(res.code() == HttpsURLConnection.HTTP_OK){
                                        if(res.body()==true) {
                                            Toast.makeText(getContext(), "댓글 삭제 완료", Toast.LENGTH_SHORT).show();
                                            bind();
                                        }
                                        else{
                                            Toast.makeText(getContext(), "삭제 권한이 없는 댓글입니다. ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(getContext(), "삭제 실패", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .subscribe());
                        break;
                }
            } else {
                Toast.makeText(getContext(), "본인의 댓글만 삭제 가능합니다.", Toast.LENGTH_SHORT).show();
            }
            return false;

        });

        if (comments.toString() == "[]") {
            rv.setAdapter(new MeetingCommentAdapter(comments, getContext(), this));
            setListViewHeightBasedOnChildren(rv);
            rv.setEmptyView(emptyCommentview);

        } else {
            rv.setAdapter(new MeetingCommentAdapter(comments, getContext(), this));
            setListViewHeightBasedOnChildren(rv);
        }

    }

    @NonNull
    private MeetingDetailViewModel getViewModel() {
        return ((HanRiverMeetupApplication) getActivity().getApplicationContext()).getMeetingDetailViewModel();
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

    private void gradationOnListTop(View view){
        ShapeDrawable.ShaderFactory sf = new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                LinearGradient lg = new LinearGradient(0, 0, 0, height,
                        // 그라데이션 색상이 들어가는 배열.
//                        new int[]{Color.parseColor("#1A75F0"),Color.parseColor("#1B70F3"),Color.parseColor("#1A7AEB"),Color.parseColor("#1985E1"),Color.parseColor("#178FDA"),Color.parseColor("#18B1DA")},
                        new int[]{Color.parseColor("#2186f8"),Color.parseColor("#1e8bf4"),Color.parseColor("#1a92ef"),Color.parseColor("#169be8"),Color.parseColor("#11a3e1"),Color.parseColor("#00c0c9")},
                        // 각 색상별 포지션 지정하는 배열. 최소값은 0이고 최대값을 1이다.
                        new float[]{0,0.2f,0.4f,0.6f,0.8f,1},
//                        new float[]{0,1},
                        // 뷰의 크기에 따라서 적용될 것이기 때문에 뭘 지정해도 큰 차이가 없다.
                        Shader.TileMode.REPEAT);
                return lg;
            }
        };
        PaintDrawable pd = new PaintDrawable();
        pd.setShape(new RectShape());
        pd.setShaderFactory(sf);

// PaintDrawable 객체를 뷰에 적용
        view.setBackground(pd);
    }
}

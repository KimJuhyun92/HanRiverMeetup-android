package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Detail.MeetingCommentAdapter;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.ApplicantVO;
import com.depromeet.hanriver.hanrivermeetup.service.CommunicationService;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MeetingDetailFragment extends DialogFragment {

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    MeetingDetailViewModel meetingDetailViewModel;
    MeetingCommentViewModel meetingCommentViewModel;
    RecyclerView rv;
    RecyclerView.LayoutManager rvManager;
    Button comment_btn, join_btn;
    EditText comment_text;
    ImageView profile_img;
    ImageButton back_btn,modify_btn;
    ScrollView scroll;
    TextView room_title, profile_name, detail_info, detail_location, detail_content, joinbtn_border;
    int meeting_seq;
    String room_master_name;
    MeetingDetailFragment self;
    RelativeLayout rl;


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
        joinbtn_border =v.findViewById(R.id.border_join_Btn);
        scroll = v.findViewById(R.id.detail_scroll);
        scroll.setOverScrollMode(View.OVER_SCROLL_NEVER);
        back_btn = v.findViewById(R.id.detail_back_btn);
        back_btn.setOnClickListener(back_click);
        rv = v.findViewById(R.id.detail_comment_rv);
        rvManager = new LinearLayoutManager(getContext());
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
                MeetingJoinFragment dialog = MeetingJoinFragment.newInstance(meeting_seq, room_master_name,self);
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
                comment.setUserID(LoginFragment.getUser_id());

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
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
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

    private void getApplicationSeq(@NonNull final List<ApplicantVO> applicantVOs){
        for(int i=0;i<applicantVOs.size();i++) {
            if (applicantVOs.get(i).getUserId().equals(LoginFragment.getUser_id())) {
                join_btn.setBackgroundColor(Color.parseColor("#aaaaaa"));
                join_btn.setEnabled(false);
                join_btn.setText("이미 참여한 모임입니다");
                join_btn.setTextColor(Color.parseColor("#ffffff"));
                joinbtn_border.setBackgroundColor(Color.parseColor("#dcdcdc"));

            }
        }
    }

    private void setMeetingDetail(@NonNull final MeetingDetail meetingDetail) {
        room_master_name = meetingDetail.getNickname();
//        gridview.setAdapter(new GridAdapter(getActivity(),activites));
        room_title.setText("" + meetingDetail.getTitle());
        profile_name.setText("" + meetingDetail.getNickname());
        String time = meetingDetail.getMeeting_time();
        time = time.substring(11, 16);
        detail_info.setText(Html.fromHtml("시간 " + "<b>"+time+"</b>"+ " / 인원 " + "<b>"+String.valueOf(meetingDetail.getParticipants_cnt())
                + "명</b> / 회비 " +"<b>"+ String.valueOf(meetingDetail.getExpected_cost()) +"</b>"+ "원"));
        detail_location.setText(meetingDetail.getMeeting_location() + "");
        detail_content.setText(meetingDetail.getDescription() + "");
        Picasso.get().load(FacebookService.getInstance().getProfileURL(meetingDetail.getUser_id()))
                .transform(CircleTransform.getInstance()).into(profile_img);

        if (meetingDetail.getUser_id().equals(LoginFragment.getUser_id())) {
//            join_btn.setBackgroundColor(Color.parseColor("#aaaaaa"));
//            join_btn.setEnabled(false);
//            join_btn.setText("내가 만든 방입니다");
//            join_btn.setTextColor(Color.parseColor("#ffffff"));
//            joinbtn_border.setBackgroundColor(Color.parseColor("#dcdcdc"));
            join_btn.setVisibility(View.GONE);
            joinbtn_border.setVisibility(View.GONE);
            RelativeLayout.LayoutParams lp  =  (RelativeLayout.LayoutParams) join_btn.getLayoutParams();
            lp.setMargins(0,0,0,0);
            join_btn.setLayoutParams(lp);
            modify_btn.setVisibility(View.VISIBLE);
        }
        // TestFrag frag = new TestFrag();
//        FragmentManager fragmentManager = getFragmentManager();


//        mActivitesView.setText("한강에서\n" +
//                "원하는 모임을 선택하세요");
    }

    private void setComments(@NonNull final List<Comment> comments) {


        if (comments.toString() == "[]")
            rv.setMinimumHeight(358);

        else {
            rv.setLayoutManager(rvManager);
            rv.setAdapter(new MeetingCommentAdapter(comments, getContext(), this));
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
}

package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.JoinRequest;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.network.GuestAPIService;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.depromeet.hanriver.hanrivermeetup.service.GuestService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MeetingJoinFragment extends DialogFragment {
    EditText numofMem, contact, reason;
    TextView textCounter, title, nickname;
    ImageView profile_img;
    int meeting_seq;
    String room_master_name;
    Button join_btn;
    DialogFragment dial;
    ImageView back_btn;
    MeetingDetailFragment detailFragment;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    MeetingJoinViewModel meetingJoinViewModel;


    @Override
    public void onStart() {
        super.onStart();
        if(getDialog()==null)
            return;

        getDialog().getWindow().setWindowAnimations(
                R.style.dialog_animation_fade);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dial = this;

    }

    public static MeetingJoinFragment newInstance(int meeting_seq, String room_master_name, MeetingDetailFragment detailFragment) {
        Bundle args = new Bundle();
        MeetingJoinFragment fragment = new MeetingJoinFragment();
        fragment.setArguments(args);
        fragment.meeting_seq = meeting_seq;
        fragment.room_master_name = room_master_name;
        fragment.detailFragment=detailFragment;
        return fragment;
    }


    private void setupViews(View v) {
        back_btn = v.findViewById(R.id.join_back_btn);
        back_btn.setOnClickListener(back_click);
        title = v.findViewById(R.id.join_title);
        title.setText("" + room_master_name + "님의\n모임에 참여하시겠습니까?");
        nickname = v.findViewById(R.id.join_nickname);
        profile_img = v.findViewById(R.id.join_profile_img);
        numofMem = v.findViewById(R.id.join_participants_cnt);
        contact = v.findViewById(R.id.join_contact);
        reason = v.findViewById(R.id.join_reason);
        textCounter = v.findViewById(R.id.join_content_counter);
        join_btn = v.findViewById(R.id.join_btn);
        nickname.setText(PreferencesManager.getNickname());

        Picasso.get().load(FacebookService.getInstance()
                .getProfileURL(PreferencesManager.getUserID()))
                .transform(CircleTransform.getInstance()).into(profile_img);

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JoinRequest joinRequest = new JoinRequest();
                joinRequest.setApplicationTime(getCurrentTime());
                joinRequest.setContact(contact.getText().toString());
                joinRequest.setDescription(reason.getText().toString());
                joinRequest.setMeetingID(meeting_seq);
                joinRequest.setParticipantsCnt(Integer.parseInt(numofMem.getText().toString()));
                joinRequest.setUserID(PreferencesManager.getUserID());



                mCompositeDisposable.add(GuestService.getInstance().joinMeeting(joinRequest)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                detailFragment.onResume();
                dial.dismiss();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meeting_join, container, false);
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


    }

    private void unBind() {
        mCompositeDisposable.clear();
    }


    public String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdfNow.format(date);

        return formatDate;
    }

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dial.dismiss();
        }
    };
}

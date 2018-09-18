package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.CreateRoomLocationFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.DatePickerFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.TimePickerFragment;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MeetingModifyRoom extends DialogFragment {

    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private MeetingDetail meetingDetail;
    MeetingDetailFragment fragment;
    DialogFragment dial;
    EditText roomname,roomcontent,contact,fee,num;
    TextView location,time,nickname,date;
    ImageButton profileimg,back_btn;
    Button modifybtn;
    RelativeLayout rl;


    @Override
    public void onStart() {
        super.onStart();
        if(getDialog()==null)
            return;

        getDialog().getWindow().setWindowAnimations(
                R.style.dialog_animation_fade);
    }

    public static MeetingModifyRoom newInstance(MeetingDetail meetingDetail,MeetingDetailFragment frag) {

        Bundle args = new Bundle();
        MeetingModifyRoom fragment = new MeetingModifyRoom();
        fragment.setArguments(args);
        fragment.fragment = frag;
        fragment.meetingDetail = meetingDetail;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dial = this;
        View v = inflater.inflate(R.layout.fragment_meeting_modifyroom, container, false);
        setupViews(v);
        mCompositeDisposable = new CompositeDisposable();
        return v;
    }

    private void setupViews(View v) {

        //화면 터치 시, 키보드 내려가게 하기 위한 클릭 이벤트.
        rl = v.findViewById(R.id.modify_room_rl);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });


        back_btn = v.findViewById(R.id.modify_room_back_btn);
        back_btn.setOnClickListener(back_click);
        nickname = v.findViewById(R.id.modify_room_nickname);
        roomname= v.findViewById(R.id.modify_room_name);
        roomcontent= v.findViewById(R.id.modify_room_content);
        profileimg = v.findViewById(R.id.modify_room_profile_img);
        modifybtn = v.findViewById(R.id.modify_btn);
        location = v.findViewById(R.id.modify_room_location);
        time = v.findViewById(R.id.modify_room_time);
        date = v.findViewById(R.id.modify_room_date);
        contact = v.findViewById(R.id.modify_room_contact);
        fee = v.findViewById(R.id.modify_room_fee);
        num = v.findViewById(R.id.modify_room_num);
        nickname.setText(PreferencesManager.getNickname());

        //본래 정보 등록.
        roomname.setText(meetingDetail.getTitle());
        roomcontent.setText(meetingDetail.getDescription());
        location.setText(meetingDetail.getMeeting_location());
        String timestr = meetingDetail.getMeeting_time();
        timestr = timestr.substring(11, 16);
        time.setText(timestr);
        contact.setText(meetingDetail.getContact());
        fee.setText(String.valueOf(meetingDetail.getExpected_cost()));
        num.setText(String.valueOf(meetingDetail.getParticipants_cnt()));

        Picasso.get().load(FacebookService.getInstance()
                .getProfileURL(PreferencesManager.getUserID()))
                .transform(CircleTransform.getInstance())
                .into(profileimg);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datefragment = DatePickerFragment.newInstance(date);
                datefragment.show(getFragmentManager(),"DatePickerfragment_tag");
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerFragment fragment = new TimePickerFragment(time);
                fragment.show(getFragmentManager(),"TimePickerfragment_tag");
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateRoomLocationFragment dialog = CreateRoomLocationFragment.newInstance(location);
                dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
                dialog.show(getFragmentManager(), "tag");
            }
        });
        modifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeetingDetail md = new MeetingDetail();
                md.setActivity_seq(meetingDetail.getActivity_seq());
                md.setDescription(roomcontent.getText().toString());
                md.setExpected_cost(Integer.parseInt(fee.getText().toString()));
                md.setMeeting_location(location.getText().toString());
                md.setMeeting_time(getCurrentDate(time.getText().toString()));
                md.setTitle(roomname.getText().toString());
                md.setParticipants_cnt(Integer.parseInt(num.getText().toString()));
                md.setUser_id(PreferencesManager.getUserID());
                md.setContact(contact.getText().toString());

                mCompositeDisposable.add(HostService.getInstance().modifyMeeting(meetingDetail.getMeeting_seq(),md)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<MeetingDetail>() {
                            @Override
                            public void onNext(MeetingDetail meetingDetail) {

                                //targetfragment로 지정한 fragment onActivityResult함수 호출 후 종료.

                                getTargetFragment().onActivityResult(0,0,null);

                                dial.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                              Toast.makeText(getContext(), "수정 에러", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(getContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                            }
                        }));

//
            }
        });

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

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public String getCurrentDate(String time) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd ");
        String formatDate = sdfNow.format(date);
        time = time+":00";

        return formatDate+time;
    }

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dial.dismiss();
        }
    };
}
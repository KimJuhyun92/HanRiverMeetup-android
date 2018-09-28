package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import javax.net.ssl.HttpsURLConnection;

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
    EditText roomname, roomcontent, contact, fee, num;
    TextView location, time, nickname, date;
    TextView tv_location, tv_time, tv_num, tv_fee, tv_contact;
    ImageButton profileimg, back_btn;
    Button modifybtn;
    RelativeLayout rl;
    TextWatcher textWatcher;
    private boolean isValidate_roomName, isValidate_num, isValidate_fee, isValidate_contact, isValidate_total;
    private LinearLayout line_roomName, line_num, line_fee, line_contact;
    private final int COLOR_DEFAULT = R.drawable.border_bottom;
    private final int COLOR_RED = R.drawable.border_bottom_red;


    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;

        getDialog().getWindow().setWindowAnimations(
                R.style.dialog_animation_move_to_up);
    }

    public static MeetingModifyRoom newInstance(MeetingDetail meetingDetail, MeetingDetailFragment frag) {

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
        initValidate();
        setupViews(v);
        mCompositeDisposable = new CompositeDisposable();
        return v;
    }

    private void setupViews(View v) {
        SetupTextWatcher();

        //화면 터치 시, 키보드 내려가게 하기 위한 클릭 이벤트.
        rl = v.findViewById(R.id.modify_room_rl);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        tv_contact = v.findViewById(R.id.modify_room_tv_contact);
        tv_fee = v.findViewById(R.id.modify_room_tv_fee);
        tv_location = v.findViewById(R.id.modify_room_tv_location);
        tv_num = v.findViewById(R.id.modify_room_tv_num);
        tv_time = v.findViewById(R.id.modify_room_tv_time);
        line_roomName = v.findViewById(R.id.meeting_modify_ll0);
        line_num = v.findViewById(R.id.meeting_modify_ll3);
        line_fee = v.findViewById(R.id.meeting_modify_ll4);
        line_contact = v.findViewById(R.id.meeting_modify_ll5);
        back_btn = v.findViewById(R.id.modify_room_back_btn);
        back_btn.setOnClickListener(back_click);
        nickname = v.findViewById(R.id.modify_room_nickname);
        roomname = v.findViewById(R.id.modify_room_name);
        roomcontent = v.findViewById(R.id.modify_room_content);
        profileimg = v.findViewById(R.id.modify_room_profile_img);
        modifybtn = v.findViewById(R.id.modify_btn);
        location = v.findViewById(R.id.modify_room_location);
        time = v.findViewById(R.id.modify_room_time);
        date = v.findViewById(R.id.modify_room_date);
        contact = v.findViewById(R.id.modify_room_contact);
        fee = v.findViewById(R.id.modify_room_fee);
        num = v.findViewById(R.id.modify_room_num);
        nickname.setText(PreferencesManager.getNickname());

        num.addTextChangedListener(textWatcher);
        fee.addTextChangedListener(textWatcher);
        contact.addTextChangedListener(textWatcher);
        roomname.addTextChangedListener(textWatcher);

        //본래 정보 등록.
        roomname.setText(meetingDetail.getTitle());
        roomcontent.setText(meetingDetail.getDescription());
        location.setText(meetingDetail.getMeeting_location());
        String timestr = meetingDetail.getMeeting_time();
        String datestr = timestr.substring(0, 10);
        timestr = timestr.substring(11, 16);
        date.setText(datestr);
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
//                DatePickerFragment datefragment = DatePickerFragment.newInstance(date);
//                datefragment.show(getFragmentManager(),"DatePickerfragment_tag");
                Toast.makeText(getContext(), "수정 불가능한 항목입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "수정 불가능한 항목입니다.", Toast.LENGTH_SHORT).show();
//                TimePickerFragment fragment = new TimePickerFragment(time);
//                fragment.show(getFragmentManager(),"TimePickerfragment_tag");
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
                if (isValidate_total == false) {
                    if (isValidate_contact == false) {
                        Toast.makeText(getContext(), "연락처 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_contact, contact);
                    } else if (isValidate_fee == false) {
                        Toast.makeText(getContext(), "회비 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_fee, fee);
                    } else if (isValidate_num == false) {
                        Toast.makeText(getContext(), "참가인원 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_num, num);
                    } else if (isValidate_roomName == false) {
                        Toast.makeText(getContext(), "방제목 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_roomName, roomname);
                    }
                } else if (isValidate_total == true) {
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

                    mCompositeDisposable.add(HostService.getInstance().modifyMeeting(meetingDetail.getMeeting_seq(), md)
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnNext(res -> {
                                if (res.code() == HttpsURLConnection.HTTP_OK) {
                                    getTargetFragment().onActivityResult(0, 0, null);
                                    Toast.makeText(getContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                                    dial.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "수정 실패", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .subscribe());
                }

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

    private void SetupTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (roomname.getText().hashCode() == charSequence.hashCode()) {
                    if (roomname.getText().length() >= 4 && roomname.getText().length() <= 20) {
                        isValidate_roomName = true;
                        isValidateTotal();
                        validateColor(line_roomName);
                    } else {

                        isValidate_roomName = false;
                        isValidateTotal();
                    }
                } else if (num.getText().hashCode() == charSequence.hashCode()) {
                    if (isInt(num.getText().toString())) {
                        isValidate_num = true;
                        isValidateTotal();
                        validateColor(line_num);
                    } else {
                        tv_num.setTextColor(Color.parseColor("#000000"));
                        isValidate_num = false;
                        isValidateTotal();
                    }
                } else if (fee.getText().hashCode() == charSequence.hashCode()) {
                    if (isInt(fee.getText().toString())) {
                        isValidate_fee = true;
                        isValidateTotal();
                        validateColor(line_fee);
                    } else {
                        tv_fee.setTextColor(Color.parseColor("#000000"));
                        isValidate_fee = false;
                        isValidateTotal();
                    }
                } else if (contact.getText().hashCode() == charSequence.hashCode()) {
                    if (contact.getText().length() >= 4 && contact.getText().length() < 15) {
                        isValidate_contact = true;
                        isValidateTotal();
                        validateColor(line_contact);
                    } else {
                        tv_contact.setTextColor(Color.parseColor("#000000"));
                        isValidate_contact = false;
                        isValidateTotal();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }


    public boolean isInt(String str) {
        return (str.lastIndexOf("-") == 0 && !str.equals("-0")) ? str.substring(1).matches(
                "\\d+") : str.matches("\\d+");
    }

    private void initValidate() {
        isValidate_fee = false;
        isValidate_contact = false;
        isValidate_num = false;
        isValidate_roomName = false;
        isValidate_total = false;
    }

    private void isValidateTotal() {
        if (isValidate_roomName == true && isValidate_num == true && isValidate_contact == true && isValidate_fee == true)
            isValidate_total = true;
        else
            isValidate_total = false;
    }

    private void invalidateColor(LinearLayout ll, EditText text) {
        ll.setBackgroundResource(COLOR_RED);
        text.setHintTextColor(Color.parseColor("#d34040"));
        text.setText(null);
    }

    private void validateColor(LinearLayout ll) {
        ll.setBackgroundResource(COLOR_DEFAULT);
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
        time = time + ":00";

        return formatDate + time;
    }

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dial.dismiss();
        }
    };
}
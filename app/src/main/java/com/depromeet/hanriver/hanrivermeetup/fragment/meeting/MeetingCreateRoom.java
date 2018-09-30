package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.CreateRoom.ExpandableListAdapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.CreateRoomLocationFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.DatePickerFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.TimePickerFragment;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.CreateRoom;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MeetingCreateRoom extends DialogFragment {

    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private boolean isValidate_roomName,isValidate_num,isValidate_fee,isValidate_contact,isValidate_total;
    private LinearLayout line_roomName,line_num,line_fee,line_contact;
    EditText roomname, roomcontent, contact, fee, num;
    TextView tv_location, tv_time, tv_num, tv_fee, tv_contact;
    TextView location, time, nickname, date;
    ImageButton profileimg, back_btn;
    Button createbtn;
    int hour, minute;
    int activity_seq;
    DialogFragment dial;
    MeetingListFragment fragment;
    RelativeLayout rl;
    TextWatcher textWatcher;
    private final int COLOR_DEFAULT=R.drawable.border_bottom;
    private final int COLOR_RED = R.drawable.border_bottom_red;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;

        getDialog().getWindow().setWindowAnimations(
                R.style.dialog_animation_move_to_up);
    }

    public static MeetingCreateRoom newInstance(int activity_seq, MeetingListFragment frag) {

        Bundle args = new Bundle();
        MeetingCreateRoom fragment = new MeetingCreateRoom();
        fragment.setArguments(args);
        fragment.activity_seq = activity_seq;
        fragment.fragment = frag;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dial = this;
        View v = inflater.inflate(R.layout.fragment_meeting_createroom, container, false);
        initValidate();
        setupViews(v);
        mCompositeDisposable = new CompositeDisposable();

        return v;
    }

    private void setupViews(View v) {
        //edittext입력 이벤트 객체 생성.
        SetupTextWatcher();

        //화면 터치 시, 키보드 내려가게 하기 위한 클릭 이벤트.
        rl = v.findViewById(R.id.create_room_rl);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        line_roomName = v.findViewById(R.id.meeting_create_ll0);
        line_num  = v.findViewById(R.id.meeting_create_ll3);
        line_fee = v.findViewById(R.id.meeting_create_ll4);
        line_contact = v.findViewById(R.id.meeting_create_ll5);
        tv_contact = v.findViewById(R.id.create_room_tv_contact);
        tv_fee = v.findViewById(R.id.create_room_tv_fee);
        tv_location = v.findViewById(R.id.create_room_tv_location);
        tv_num = v.findViewById(R.id.create_room_tv_num);
        tv_time = v.findViewById(R.id.create_room_tv_time);
        back_btn = v.findViewById(R.id.create_room_back_btn);
        back_btn.setOnClickListener(back_click);
        nickname = v.findViewById(R.id.create_room_nickname);
        roomname = v.findViewById(R.id.create_room_name);
        roomcontent = v.findViewById(R.id.create_room_content);
        profileimg = v.findViewById(R.id.create_room_profile_img);
        createbtn = v.findViewById(R.id.create_btn);
        location = v.findViewById(R.id.create_room_location);
        time = v.findViewById(R.id.create_room_time);
        date = v.findViewById(R.id.create_room_date);
        contact = v.findViewById(R.id.create_room_contact);
        fee = v.findViewById(R.id.create_room_fee);
        num = v.findViewById(R.id.create_room_num);
        nickname.setText(PreferencesManager.getNickname());

        num.addTextChangedListener(textWatcher);
        fee.addTextChangedListener(textWatcher);
        contact.addTextChangedListener(textWatcher);
        roomname.addTextChangedListener(textWatcher);

        Picasso.get().load(FacebookService.getInstance().getProfileURL(PreferencesManager.getUserID()))
                .transform(CircleTransform.getInstance()).fit().centerCrop().into(profileimg);

        date.setText(getCurrentDate());
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
                fragment.show(getFragmentManager(), "TimePickerfragment_tag");
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
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate_total==false){
                    if(isValidate_contact==false){
                        Toast.makeText(getContext(), "연락처 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_contact,contact);
                    }
                    else if(isValidate_fee==false){
                        Toast.makeText(getContext(), "회비 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_fee,fee);
                    }
                    else if(isValidate_num==false){
                        Toast.makeText(getContext(), "참가인원 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_num,num);
                    }
                    else if(isValidate_roomName==false){
                        Toast.makeText(getContext(), "방제목 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_roomName,roomname);
                    }
                }
                else {
                    //
                    MeetingDetail md = new MeetingDetail();
                    md.setActivity_seq(activity_seq);
                    md.setDescription(roomcontent.getText().toString());
                    md.setExpected_cost(Integer.parseInt(fee.getText().toString()));
                    md.setMeeting_location(location.getText().toString());
                    md.setMeeting_time(getResultDate(date.getText().toString(),time.getText().toString()));
                    md.setTitle(roomname.getText().toString());
                    md.setParticipants_cnt(Integer.parseInt(num.getText().toString()));
                    md.setUser_id(PreferencesManager.getUserID());
                    md.setContact(contact.getText().toString());

                    mCompositeDisposable.add(HostService.getInstance().createMeeting(md)
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnNext(res->{
                                if(res.code() == HttpsURLConnection.HTTP_OK){
                                    getFragmentManager().findFragmentById(R.id.root_frame);
                                    getFragmentManager().beginTransaction()
                                            .detach(fragment)
                                            .attach(fragment)
                                            .commit();

                                    dial.dismiss();
                                }
                                else{
                                    Toast.makeText(getContext(), "해당 날짜에 이미 방을 만들었습니다.", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public String getResultDate(String date,String time) {
        time = time + ":00";
        String result_meeting_time = date + " " + time;
        return result_meeting_time;
    }

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dial.dismiss();
        }
    };

    private void SetupTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(roomname.getText().hashCode() == charSequence.hashCode()){
                    if(roomname.getText().length()>=4&&roomname.getText().length()<=20){
                        isValidate_roomName=true;isValidateTotal();
                        validateColor(line_roomName);

                    }
                    else{
                        roomname.setTextColor(getResources().getColor(R.color.warm_grey));
                        isValidate_roomName=false;isValidateTotal();
                    }
                }
                else if (num.getText().hashCode() == charSequence.hashCode()) {
                    if (isInt(num.getText().toString())&&num.getText().toString().length()<=2) {
                        isValidate_num=true;isValidateTotal();
                        validateColor(line_num);
                    } else {
                        num.setTextColor(getResources().getColor(R.color.warm_grey));
                        isValidate_num=false;isValidateTotal();
                    }
                }
                else if(fee.getText().hashCode()==charSequence.hashCode()){
                    if(isInt(fee.getText().toString())&&fee.getText().toString().length()<=6){
                        isValidate_fee=true;isValidateTotal();
                        validateColor(line_fee);
                    }
                    else{
                        fee.setTextColor(getResources().getColor(R.color.warm_grey));
                        isValidate_fee=false;isValidateTotal();
                    }
                }
                else if(contact.getText().hashCode()==charSequence.hashCode()){
                    if(contact.getText().length()>=4&&contact.getText().length()<15){
                        isValidate_contact=true;isValidateTotal();
                        validateColor(line_contact);
                    }
                    else{
                        contact.setTextColor(getResources().getColor(R.color.warm_grey));
                        isValidate_contact=false;isValidateTotal();
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

    private void initValidate(){
        isValidate_fee=false;
        isValidate_contact=false;
        isValidate_num=false;
        isValidate_roomName=false;
        isValidate_total=false;
    }

    private void isValidateTotal(){
        if(isValidate_roomName==true && isValidate_num==true && isValidate_contact==true && isValidate_fee==true)
            isValidate_total = true;
        else
            isValidate_total = false;
    }

    private void invalidateColor(LinearLayout ll,EditText text){
        ll.setBackgroundResource(COLOR_RED);
        text.setHintTextColor(Color.parseColor("#d34040"));
        text.setText(null);
    }

    private void validateColor(LinearLayout ll){
        ll.setBackgroundResource(COLOR_DEFAULT);
    }

    public String getCurrentDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdfNow.format(date);
        formatDate = formatDate.substring(0,10);
        return formatDate;
    }
}

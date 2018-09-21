package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import javax.net.ssl.HttpsURLConnection;

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
    private TextView tv_num,tv_contact;
    private LinearLayout line_num,line_contact;
    private TextWatcher textWatcher;
    private boolean isValidate_contact,isValidate_num,isValidate_total,isValidate_reason;
    private final int COLOR_DEFAULT=R.drawable.border_bottom;
    private final int COLOR_RED = R.drawable.border_bottom_red;

    @NonNull
    private CompositeDisposable mCompositeDisposable;


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
        SetupTextWatcher();

        tv_contact = v.findViewById(R.id.meeting_join_tv_contact);
        tv_num = v.findViewById(R.id.meeting_join_tv_num);
        line_num = v.findViewById(R.id.meeting_join_ll1);
        line_contact = v.findViewById(R.id.meeting_join_ll2);
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

        numofMem.addTextChangedListener(textWatcher);
        contact.addTextChangedListener(textWatcher);
        reason.addTextChangedListener(textWatcher);

        Picasso.get().load(FacebookService.getInstance()
                .getProfileURL(PreferencesManager.getUserID()))
                .transform(CircleTransform.getInstance()).into(profile_img);

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isValidate_total == false){
                    if(isValidate_contact==false){
                        Toast.makeText(getContext(), "연락처 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_contact,contact);
                    }
                    else if(isValidate_num==false){
                        Toast.makeText(getContext(), "참가인원 오류", Toast.LENGTH_SHORT).show();
                        invalidateColor(line_num,numofMem);
                    } else if (isValidate_reason == false) {
                        Toast.makeText(getContext(), "참여이유를 1~150자 이내로 적어주세요", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
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
                            .doOnNext(res -> {
                                if (res.code() == HttpsURLConnection.HTTP_OK) {
                                    detailFragment.onResume();
                                    dial.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "참가 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .subscribe());
                }
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

    private void initValidate(){
        isValidate_contact = false;
        isValidate_num = false;
        isValidate_total = false;
        isValidate_reason = false;
    }

    private void isValidateTotal(){
        if(isValidate_num==true && isValidate_contact==true && isValidate_reason==true)
            isValidate_total = true;
        else
            isValidate_total = false;
    }

    private void invalidateColor(LinearLayout ll, EditText text){
        ll.setBackgroundResource(COLOR_RED);
        text.setHintTextColor(Color.parseColor("#d34040"));
        text.setText(null);
    }

    public boolean isInt(String str) {
        return (str.lastIndexOf("-") == 0 && !str.equals("-0")) ? str.substring(1).matches(
                "\\d+") : str.matches("\\d+");
    }

    private void validateColor(LinearLayout ll){
        ll.setBackgroundResource(COLOR_DEFAULT);
    }

    private void SetupTextWatcher() {
        textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(numofMem.getText().hashCode()== charSequence.hashCode()){
                    if (isInt(numofMem.getText().toString())) {
                        isValidate_num=true; isValidateTotal();
                        validateColor(line_num);
                    }
                    else{
                        tv_num.setTextColor(Color.parseColor("#000000"));
                        isValidate_num=false; isValidateTotal();
                    }
                }
                else if(contact.getText().hashCode()==charSequence.hashCode()){
                    if(contact.getText().length()>=4&&contact.getText().length()<15){
                        isValidate_contact=true; isValidateTotal();
                        validateColor(line_contact);
                    }
                    else{
                        tv_contact.setTextColor(Color.parseColor("#000000"));
                        isValidate_contact=false; isValidateTotal();
                    }
                }
                else if(reason.getText().hashCode()==charSequence.hashCode()){
                    textCounter.setText("["+reason.getText().length()+"/150]");
                    if(reason.getText().length()<=150){
                        isValidate_reason=true; isValidateTotal();
                        textCounter.setTextColor(Color.parseColor("#000000"));
                    }
                    else{
                        isValidate_reason=false; isValidateTotal();
                        textCounter.setTextColor(Color.parseColor("#d34040"));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }
}

package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.AlarmAdapter;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.AlarmDetail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class AlarmDialog extends Dialog {

    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private ImageButton closeButton;

    private List<AlarmDetail> alarmDetails;
    private List<AlarmDetail> todayDetail = new ArrayList<AlarmDetail>();
    private List<AlarmDetail> lastDetail = new ArrayList<AlarmDetail>();

    private RecyclerView todayRecyclerView;
    private RecyclerView lastRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AlarmAdapter todayAlarmAdapter;
    private AlarmAdapter lastAlarmAdapter;


    public AlarmDialog(@NonNull Context context, List<AlarmDetail> alarmDetails) {
        super(context);
        this.alarmDetails = alarmDetails;

        Initialize();

        closeButton = (ImageButton) findViewById(R.id.alarm_close_btn);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        setupView();

        for(int i = 0; i < alarmDetails.size(); i++){

            if(DistinguishTime(alarmDetails.get(i).getCreationTime()) == 0) {
                todayDetail.add(alarmDetails.get(i));
            }
            else {
                lastDetail.add(alarmDetails.get(i));
            }
        }

        todayAlarmAdapter = new AlarmAdapter(context, todayDetail);
        todayRecyclerView.setAdapter(todayAlarmAdapter);

        lastAlarmAdapter = new AlarmAdapter(context, lastDetail);
        lastRecyclerView.setAdapter(lastAlarmAdapter);

    }

    private void setupView(){
        //today list
        todayRecyclerView = findViewById(R.id.today_recyclerview);
        todayRecyclerView.setHasFixedSize(true);
        todayRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mLayoutManager = new LinearLayoutManager(getContext());
        todayRecyclerView.setLayoutManager(mLayoutManager);

        //last list
        lastRecyclerView = findViewById(R.id.last_recyclerview);
        lastRecyclerView.setHasFixedSize(true);
        lastRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mLayoutManager = new LinearLayoutManager(getContext());
        lastRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void Initialize() {
        //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //다이얼로그의 배경을 투명으로 만듭니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //다이얼로그에서 사용할 레이아웃입니다.
        setContentView(R.layout.alarm_dialog);
    }

    //오늘, 과거 알람 구별하여 어댑터 연동 로직
    private int DistinguishTime(String time){

        //받아온 시간비교을 위한 변수
        String creationTime[];
        String checkTime[];

        //현재 시간비교를 위한 변수
        String nowTime[];

        //현재 날짜 및 시간을 담는 변수
        String now_date;
        String now_time;

        //받아온 시간을 스플릿
        creationTime = time.split(" ");
        checkTime = creationTime[1].split(":");

        now_date = getDate();
        now_time = getTime();
        nowTime = now_time.split(":");

        if(creationTime[0].compareTo(now_date) == 0){
            if(checkTime[0] == nowTime[0]) {
                if (checkTime[1] == nowTime[1])
                    return 0;
                else
                    return 0;
            }
            else
                return 0;
        }
        else
            return 1;
    }

    public String getDate(){
        long mNow;
        Date mDate;
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    public String getTime(){
        long mNow;
        Date mDate;
        SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm:ss");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}

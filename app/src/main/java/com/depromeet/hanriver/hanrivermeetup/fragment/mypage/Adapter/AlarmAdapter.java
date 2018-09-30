package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.AlarmDetail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ItemViewHolder>{

    private LayoutInflater inflater;
    private Context mContext;
    private List<AlarmDetail> mItems;
    private android.app.Activity mAct;

    public AlarmAdapter(Context context, List<AlarmDetail> items) {
//        mAct = act;
        mContext = context;
        mItems = items;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.alarm_item, parent, false);
        return new AlarmAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int i) {

        holder.mTitle.setText(mItems.get(i).getMessage());
        holder.mTime.setText(DistinguishTime(mItems.get(i).getCreationTime()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mTime;

        public ItemViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.alarm_title);
            mTime = view.findViewById(R.id.alarm_time);
        }
    }

    //오늘, 과거 알람 구별
    private String DistinguishTime(String time){

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

        Log.d("@@@@check time@@@", ""+ Integer.valueOf(checkTime[0]));
        Log.d("@@@@now time@@@", ""+ Integer.valueOf(nowTime[0]));
        Log.d("@@@@abs@@@", ""+ Math.abs(Integer.valueOf(checkTime[0]) - Integer.valueOf(nowTime[0])) + "시간 전");


        if(creationTime[0].compareTo(now_date) == 0){
            if(checkTime[0] == nowTime[0]) {
                if (checkTime[1] == nowTime[1])
                    return ( Math.abs(Integer.valueOf(checkTime[2]) - Integer.valueOf(nowTime[2])) + "초 전");
                else
                    return ( Math.abs(Integer.valueOf(checkTime[1]) - Integer.valueOf(nowTime[1])) + "분 전");
            }
            else
                return ( Math.abs(Integer.valueOf(checkTime[0]) - Integer.valueOf(nowTime[0])) + "시간 전");
        }
        else
            return creationTime[0];
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

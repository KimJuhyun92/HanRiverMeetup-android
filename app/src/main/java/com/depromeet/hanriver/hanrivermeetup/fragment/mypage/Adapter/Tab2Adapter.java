package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingDetailFragment;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.annotations.Nullable;

public class Tab2Adapter extends BaseAdapter{
    private List<Tab2VO> mItems;
    private Fragment fragment;
    private ViewHolder holder = new ViewHolder();


    public Tab2Adapter (List<Tab2VO> items, Fragment fragment) {
        super();
        mItems = items;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

//        ViewHolder holder = new ViewHolder();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mypage_tab2_item, viewGroup, false);

            holder.mTitle = (TextView)view.findViewById(R.id.title);
            holder.mDate = (TextView)view.findViewById(R.id.meeting_date);
            holder.mTime = (TextView)view.findViewById(R.id.meeting_time);
            holder.mLocation = (TextView)view.findViewById(R.id.meeting_location);
            holder.mState = (TextView) view.findViewById(R.id.state);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        String meeting_date_month;
        String meeting_date_day;
        String meeting_time;
        String meetingDate[] = mItems.get(position).getMeetingDetail().getMeetingTime().split(" |-");
        meeting_date_month = meetingDate[1];
        meeting_date_day = meetingDate[2];
        meeting_time = meetingDate[3];

        //data 처리
        String creation_date;
        String now_date;
        String creationDate[] = mItems.get(position).getMeetingDetail().getCreationTime().split(" ");
        creation_date = creationDate[0];

        //매칭 실패, 지난 모임 구분 logic
        now_date = getTime();

        if(creation_date.compareTo(now_date) == 0){
            if(mItems.get(position).getMeetingDetail().getContactSeq() == 0)
                //대기중
                holder.mState.setText("대기중");
            else if(mItems.get(position).getMeetingDetail().getContactSeq() != 0)
                //매칭 실패
                holder.mState.setText("매칭실패");
        }
        //지난 모임
        else
            holder.mState.setText("매칭실패");

        holder.mTitle.setText(mItems.get(position).getMeetingDetail().getTitle());
        holder.mDate.setText(meeting_date_month + "월 " + meeting_date_day + "일");
        holder.mTime.setText("시간 " + meeting_time.substring(0,5));
        holder.mLocation.setText(mItems.get(position).getMeetingDetail().getMeetingLocation());

        return view;
    }

    public class ViewHolder {
        private TextView mTitle;
        private TextView mDate;
        private TextView mTime;
        private TextView mLocation;
        private TextView mState;
    }

    public String getTime(){
        long mNow;
        Date mDate;
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}

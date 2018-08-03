package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.annotations.Nullable;

public class Tab2Adapter extends BaseAdapter{
    private LayoutInflater inflater;
    List<Tab2VO> mItems;
    private Context mContext;


    public Tab2Adapter (List<Tab2VO> items) {
        super();
        mItems = items;

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

        ViewHolder holder = new ViewHolder();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mypage_tab2_item, viewGroup, false);

            holder.mTitle = (TextView)view.findViewById(R.id.title);
            holder.mDate = (TextView)view.findViewById(R.id.date);
            holder.mTime = (TextView)view.findViewById(R.id.meeting_time);
            holder.mCost = (TextView)view.findViewById(R.id.expected_cost);
            holder.mParticipants=(TextView)view.findViewById(R.id.participants_cnt);
            holder.mState = (ImageView)view.findViewById(R.id.state);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        //data 처리
        String meeting_date;
        String meeting_time;
        String creation_date;
        String now_date;

        String meetingDate[] = mItems.get(position).getMeetingDetail().getMeetingTime().split(" ");
        meeting_date = meetingDate[0];
        meeting_time = meetingDate[1];

//        String creationDate[] = mItems.get(position).getCreationTime().split("-| ");
//        creation_date = creationDate[2];
        String creationDate[] = mItems.get(position).getMeetingDetail().getCreationTime().split(" ");
        creation_date = creationDate[0];

        //매칭 실패, 지난 모임 구분 logic
        now_date = getTime();

        Log.d("@@@day1",""+creation_date);
        Log.d("@@@day2",""+now_date);

        if(creation_date.compareTo(now_date) == 0){
            if(mItems.get(position).getMeetingDetail().getContactSeq() == 0)
                //대기중
                holder.mState.setImageResource(R.drawable.ic_matching_wating);
            else if(mItems.get(position).getMeetingDetail().getContactSeq() != 0)
                //매칭 실패
                holder.mState.setImageResource(R.drawable.ic_matching_fail);
        }
        //지난 모임
        else
            holder.mState.setImageResource(R.drawable.ic_matching_last);



        holder.mTitle.setText(mItems.get(position).getMeetingDetail().getTitle());

        holder.mDate.setText(meeting_date);
        holder.mTime.setText(meeting_time.substring(0,5));

        holder.mCost.setText(String.valueOf(mItems.get(position).getMeetingDetail().getExpectedCost()));
        holder.mParticipants.setText(String.valueOf(mItems.get(position).getMeetingDetail().getParticipantsCnt()));


        return view;
    }

    public class ViewHolder {
        private TextView mTitle;
        private TextView mDate;
        private TextView mTime;
        private TextView mCost;
        private TextView mParticipants;
        private ImageView mState;
    }

    private String getTime(){
        long mNow;
        Date mDate;
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}

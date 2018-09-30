package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ContactDialog;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;

import java.util.List;

public class Tab3Adapter extends RecyclerView.Adapter<Tab3Adapter.ItemViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private List<Tab3VO> mItems;
    private android.app.Activity mAct;
    private Dialog dialog;

    public Tab3Adapter(android.app.Activity act, List<Tab3VO> items) {
        mAct = act;
        mItems = items;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.mypage_tab3_item, parent, false);
        return new Tab3Adapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        String meeting_date_month;
        String meeting_date_day;
        String meeting_time;
        String meetingDate[] = mItems.get(position).getMeetingDetail().getMeetingTime().split(" |-");
        meeting_date_month = meetingDate[1];
        meeting_date_day = meetingDate[2];
        meeting_time = meetingDate[3];

        holder.mTitle.setText(mItems.get(position).getMeetingDetail().getTitle());
        holder.mLocation.setText(mItems.get(position).getMeetingDetail().getMeetingLocation());
        holder.mDate.setText(meeting_date_month + "월 " + meeting_date_day + "일");
        holder.mTime.setText("시간 " + meeting_time.substring(0,5));

        //참가자인지 주최자인지 구별하는 Logic
        if(TextUtils.equals(PreferencesManager.getUserID(),
                mItems.get(position).getJoinDetail().getUserId()))
        {
            //주최자
            holder.mInfoButton.setImageResource(R.drawable.ic_contact_blue_icon);
            holder.mLeftLine.setBackgroundColor(Color.parseColor("#2186f8"));

            holder.mInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog = new ContactDialog(view.getContext(), mItems.get(position).getMeetingDetail().getContact());
                    dialog.show();
                }
            });

        }
        else {
            //참가자
            holder.mInfoButton.setImageResource(R.drawable.ic_contact_green_icon);
            holder.mLeftLine.setBackgroundColor(Color.parseColor("#00c0c9"));
            holder.mInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog = new ContactDialog(view.getContext(), mItems.get(position).getMeetingDetail().getContact());
                    dialog.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    // 커스텀 뷰홀더 
// item layout 에 존재하는 위젯들을 바인딩합니다. 
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mDate;
        public TextView mTime;
        public TextView mLocation;
        public ImageView mInfoButton;
        public View mLeftLine;

        public ItemViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.title);
            mDate = view.findViewById(R.id.meeting_date);
            mTime = view.findViewById(R.id.meeting_time);
            mLocation = view.findViewById(R.id.meeting_location);
            mInfoButton = view.findViewById(R.id.info_button);
            mLeftLine = view.findViewById(R.id.leftline);
        }
    }


    public void add(Tab3VO tab3VO) {
        mItems.add(tab3VO);
    }
}

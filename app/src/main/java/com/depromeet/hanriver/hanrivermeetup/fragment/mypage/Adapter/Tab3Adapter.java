package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.SelectionDialog;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Tab3;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.TestVO;

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
        String meeting_time;
        String meetingDate[] = mItems.get(position).getMeetingDetail().getMeetingTime().split(" ");
        meeting_time = meetingDate[1];

        holder.mTitle.setText(mItems.get(position).getMeetingDetail().getTitle());
        holder.mTime.setText(meeting_time.substring(0,5));
        holder.mCost.setText(String.valueOf(mItems.get(position).getMeetingDetail().getExpectedCost()));
        holder.mParticipants.setText(String.valueOf(mItems.get(position).getMeetingDetail().getParticipantsCnt()));

        //참가자인지 주최자인지 구별하는 Logic
        if(TextUtils.equals(LoginFragment.getUser_id(), mItems.get(position).getJoinDetail().getUserId()))
        {
            holder.mInfoButton.setImageResource(R.drawable.ic_guest_button);
            holder.mInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //전화번호 데이터 처리 Logic
                    String hostCall;
                    String mCall="tel:";
                    hostCall = mItems.get(position).getMeetingDetail().getContact();

                    String phoneNum[] = hostCall.split("-");
                    hostCall = "";
                    for(int i=0; i<phoneNum.length; i++){
                        hostCall += phoneNum[i];
                    }
                    mCall += hostCall;

                    dialog = new SelectionDialog(view.getContext(), mCall);
                    dialog.show();
                }
            });

        }
        else {
            holder.mInfoButton.setImageResource(R.drawable.ic_host_button);
            holder.mInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //전화번호 데이터 처리 Logic
                    String guestCall;
                    String mCall="tel:";
                    guestCall = mItems.get(position).getJoinDetail().getContact();

                    String phoneNum[] = guestCall.split("-");
                    guestCall = "";
                    for(int i=0; i<phoneNum.length; i++){
                        guestCall += phoneNum[i];
                    }
                    mCall += guestCall;

                    dialog = new SelectionDialog(view.getContext(), mCall);
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
        public TextView mTime;
        public TextView mCost;
        public TextView mParticipants;
        public ImageView mInfoButton;

        public ItemViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.title);
            mTime = view.findViewById(R.id.meeting_time);
            mCost = view.findViewById(R.id.expected_cost);
            mParticipants = view.findViewById(R.id.participants_cnt);
            mInfoButton = view.findViewById(R.id.info_button);
        }
    }


    public void add(Tab3VO tab3VO) {
        mItems.add(tab3VO);
    }
}

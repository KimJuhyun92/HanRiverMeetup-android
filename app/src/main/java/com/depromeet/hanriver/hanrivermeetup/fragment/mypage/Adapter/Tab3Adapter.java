package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
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

        holder.mTitle.setText(mItems.get(position).getMeetingDetail().getTitle());
        holder.mTime.setText(mItems.get(position).getMeetingDetail().getMeetingTime());
        holder.mCost.setText(String.valueOf(mItems.get(position).getMeetingDetail().getExpectedCost()));
        holder.mParticipants.setText(String.valueOf(mItems.get(position).getMeetingDetail().getParticipantsCnt()));
        holder.mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String HostCall;
                String GuestCall;
                HostCall = mItems.get(position).getMeetingDetail().getContact();
                GuestCall = mItems.get(position).getJoinDetail().getContact();

                //다이얼로그창 띄우는 로직 작성 (생성자에 정보 같이넘기기)


                Toast.makeText(view.getContext(), HostCall, Toast.LENGTH_SHORT).show();
            }
        });

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
        public ImageView mCallButton;

        public ItemViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.title);
            mTime = view.findViewById(R.id.meeting_time);
            mCost = view.findViewById(R.id.expected_cost);
            mParticipants = view.findViewById(R.id.participants_cnt);
            mCallButton = view.findViewById(R.id.call_button);
        }
    }


    public void add(Tab3VO tab3VO) {
        mItems.add(tab3VO);
    }
}

package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.ApplicantVO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;

import java.util.ArrayList;
import java.util.List;

public class Tab1Adapter extends RecyclerView.Adapter<Tab1Adapter.ItemViewHolder>{
    private LayoutInflater inflater;
    private Context mContext;
    private List<Tab1VO> mItems;
    private android.app.Activity mAct;

    public Tab1Adapter(android.app.Activity act, List<Tab1VO> items) {
        mAct = act;
        mItems = items;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 커스텀 뷰홀더 
// item layout 에 존재하는 위젯들을 바인딩합니다. 
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mTime;
        public TextView mCost;
        public TextView mParticipants;
        public RecyclerView applicant_list;

        public ItemViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.title);
            mTime = view.findViewById(R.id.meeting_time);
            mCost = view.findViewById(R.id.expected_cost);
            mParticipants = view.findViewById(R.id.participants_cnt);
            applicant_list = view.findViewById(R.id.applicant_list);
        }
    }

    // 새로운 뷰 홀더 생성 
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mypage_tab1_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mTitle.setText(mItems.get(position).getTitle());
        holder.mTime.setText(mItems.get(position).getMeeting_time());
        holder.mCost.setText(String.valueOf(mItems.get(position).getExpected_cost()));
        holder.mParticipants.setText(String.valueOf(mItems.get(position).getParticipants_cnt()));


        //Horizontal RecyclerView TEST

        ArrayList<ApplicantVO> test = new ArrayList<ApplicantVO>();
        test.add(new ApplicantVO("test1",R.drawable.ic_chicken_icon));
        test.add(new ApplicantVO("test2",R.drawable.ic_camping_icon));
        test.add(new ApplicantVO("test3",R.drawable.ic_chicken_icon));
        test.add(new ApplicantVO("test4",R.drawable.ic_camping_icon));
        test.add(new ApplicantVO("test5",R.drawable.ic_camping_icon));
        test.add(new ApplicantVO("test6",R.drawable.ic_camping_icon));
        test.add(new ApplicantVO("test7",R.drawable.ic_camping_icon));
        test.add(new ApplicantVO("test8",R.drawable.ic_camping_icon));
        test.add(new ApplicantVO("test9",R.drawable.ic_camping_icon));

        ApplicantListAdapter applicantListAdapter = new ApplicantListAdapter(mContext,test);
        holder.applicant_list.setHasFixedSize(true);
        holder.applicant_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.applicant_list.setAdapter(applicantListAdapter);



    }

    // 데이터 셋의 크기를 리턴해줍니다. 
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.ApplicantVO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Tab1Adapter extends RecyclerView.Adapter<Tab1Adapter.ItemViewHolder>{
    private LayoutInflater inflater;
    private Context mContext;
    private List<Tab1VO> mItems;
    private android.app.Activity mAct;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    List<ApplicantVO> mApplicantsList = new ArrayList<ApplicantVO>();
    private ApplicantListAdapter applicantListAdapter;
    private ItemViewHolder holder;


    public Tab1Adapter(android.app.Activity act, List<Tab1VO> items) {
        mAct = act;
        mItems = items;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 커스텀 뷰홀더 
// item layout 에 존재하는 위젯들을 바인딩합니다. 
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mDate;
        public TextView mTime;
        public TextView mEmptyMessage;
        public TextView mLocation;
        public RecyclerView applicant_list;

        public ItemViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.title);
            mDate = view.findViewById(R.id.meeting_date);
            mTime = view.findViewById(R.id.meeting_time);
            mLocation = view.findViewById(R.id.meeting_location);
            mEmptyMessage = view.findViewById(R.id.empty_message);
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
    public void onBindViewHolder(ItemViewHolder viewHolder, int position) {
        holder = viewHolder;

        String meeting_date_month;
        String meeting_date_day;
        String meeting_time;
        String meetingDate[] = mItems.get(position).getMeetingTime().split(" |-");
        meeting_date_month = meetingDate[1];
        meeting_date_day = meetingDate[2];
        meeting_time = meetingDate[3];





        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mCompositeDisposable.add(HostService.getInstance().getMeetingApplicants(mItems.get(position).getMeetingSeq())
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::setApplicantListVOs));
            }

            //Setting ApplicantList
            private void setApplicantListVOs (List<ApplicantVO> applicantVOS) {
                ItemViewHolder holder = viewHolder;
                //필요정보 : meetingseq, 상대방 userID

                if(applicantVOS.size() == 0){
                    SpannableString content = new SpannableString("곧 신청자 올꺼에요!");
                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                    holder.mEmptyMessage.setText(content);
                }
                else {
                    applicantListAdapter = new ApplicantListAdapter(mContext, applicantVOS);
                    holder.applicant_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                    holder.applicant_list.setAdapter(applicantListAdapter);
                    Log.d("@@@size@@@", "" + applicantVOS.size());
                    for (int i = 0; i < applicantVOS.size(); i++) {
                        Log.d("@@@nickname@@@", i + applicantVOS.get(i).getNickname());
                    }
                }
            }
        }.handleMessage(new Message());

        holder.mTitle.setText(mItems.get(position).getTitle());
        holder.mLocation.setText(mItems.get(position).getMeetingLocation());
        holder.mDate.setText(meeting_date_month + "월 " + meeting_date_day + "일");
        holder.mTime.setText("시간 " + meeting_time.substring(0,5));

    }



    // 데이터 셋의 크기를 리턴해줍니다. 
    @Override
    public int getItemCount() {
        return mItems.size();
    }

}

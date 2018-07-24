package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Category.MeetingCategoryAdapter;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MeetingDetailFragment extends DialogFragment{

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    MeetingDetailViewModel meetingDetailViewModel;
    RecyclerView rv;
    Button comment_btn,join_btn;
    TextView room_title,profile_name,detail_info,detail_location,detail_content;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meetingDetailViewModel = getViewModel();
    }

    public static MeetingDetailFragment newInstance() {

        Bundle args = new Bundle();
        MeetingDetailFragment fragment = new MeetingDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void setupViews(View v){
        rv  = v.findViewById(R.id.detail_comment_rv);
        room_title = v.findViewById(R.id.detail_room_title);
        profile_name = v.findViewById(R.id.detail_name);
        detail_info = v.findViewById(R.id.detail_info);
        detail_location = v.findViewById(R.id.detail_location);
        detail_content = v.findViewById(R.id.detail_content);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meeting_detail,container,false);
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

        mCompositeDisposable.add(meetingDetailViewModel.getMeetingDetail()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setMeetingDetail));

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setMeetingDetail(@NonNull final MeetingDetail meetingDetail) {
//        gridview.setAdapter(new GridAdapter(getActivity(),activites));
            room_title.setText(""+meetingDetail.getRoomName());
            detail_info.setText("시간 "+meetingDetail.getTime().toString()+" / 인원 "+String.valueOf(meetingDetail.getNumOfmember()+"명 / 회비 "+String.valueOf(meetingDetail.getFee())+"원"));
            detail_location.setText(meetingDetail.getLocation()+"");
            detail_content.setText(meetingDetail.getRoomContent()+"");
            // TestFrag frag = new TestFrag();
//        FragmentManager fragmentManager = getFragmentManager();


//        mActivitesView.setText("한강에서\n" +
//                "원하는 모임을 선택하세요");
    }

    @NonNull
    private MeetingDetailViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getMeetingDetailViewModel();
    }
}

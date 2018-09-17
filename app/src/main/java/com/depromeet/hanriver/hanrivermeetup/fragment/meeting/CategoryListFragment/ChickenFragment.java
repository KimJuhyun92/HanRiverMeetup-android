package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List.MeetingListAdapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingListInnerViewModel;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ChickenFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private SwipeRefreshLayout swipeRefreshLayout;

    private int activity_seq;

    @Nullable
    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager rvManager;

    public static ChickenFragment newInstance(int activity_seq) {

        Bundle args = new Bundle();

        ChickenFragment fragment = new ChickenFragment();
        fragment.setArguments(args);
        fragment.activity_seq=activity_seq;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meeting_list_inner, container, false);
        setupViews(v);
        return v;

    }

    private void setupViews(View v) {
        recyclerView = v.findViewById(R.id.list_room_rv);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rvManager = new LinearLayoutManager(getContext());
        swipeRefreshLayout = v.findViewById(R.id.list_refresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressON();
                bind();

            }
        });

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

        mCompositeDisposable.add(HostService.getInstance().getWeekList(activity_seq)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setRooms));

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setRooms(@NonNull final List<MeetingDetail> Rooms) {
        // 새로고침 하였을 경우
        progressOFF();

        recyclerView.setLayoutManager(rvManager);
        recyclerView.setAdapter(new MeetingListAdapter(Rooms,getContext(),this));

    }

    @NonNull
    private MeetingListInnerViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getChickenListViewModel();
    }


    //targetfragment 에서 호출할때 현재 fragment 갱신
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bind();
    }

    public void progressON(){
        HanRiverMeetupApplication.getInstance().progressON(getActivity());
    }

    public void progressOFF(){
        HanRiverMeetupApplication.getInstance().progressOFF(swipeRefreshLayout);
    }
}
package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment;

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

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CampingFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private SwipeRefreshLayout swipeRefreshLayout;

    private int activity_seq;

    @NonNull
    private MeetingListInnerViewModel mViewModel;

    @Nullable
    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager rvManager;

    public static CampingFragment newInstance(int activity_seq) {

        Bundle args = new Bundle();

        CampingFragment fragment = new CampingFragment();
        fragment.setArguments(args);
        fragment.activity_seq=activity_seq;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = getViewModel();
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
                .doOnNext(res -> {
                    if(res.code() == HttpsURLConnection.HTTP_OK) {
                        setRooms(res.body());
                    }
                    else {
                        Toast.makeText(getActivity(),
                                "모임 정보를 불러오지 못했습니다. 새로고침을 해주세요.", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe());

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setRooms(@NonNull final List<MeetingDetail> Rooms) {
        progressOFF();

        recyclerView.setLayoutManager(rvManager);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setAdapter(new MeetingListAdapter(Rooms,getContext(),this));

    }

    @NonNull
    private MeetingListInnerViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getCampingListViewModel();
    }

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
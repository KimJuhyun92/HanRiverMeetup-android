package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Category.MeetingCategoryAdapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List.MeetingListAdapter;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MeetingListInnerFragment extends Fragment{
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private MeetingListInnerViewModel mViewModel;

    @Nullable
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvManager;

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

        mCompositeDisposable.add(mViewModel.getAvailableRooms()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setRooms));

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setRooms(@NonNull final List<MeetingDetail> Rooms) {

        recyclerView.setLayoutManager(rvManager);
        recyclerView.setAdapter(new MeetingListAdapter(Rooms,getContext(),this));

    }


    @NonNull
    private MeetingListInnerViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getMeetingListInnerViewModel();
    }
}

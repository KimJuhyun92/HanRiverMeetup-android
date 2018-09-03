package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class EtcFragment extends Fragment {
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
//        gridview = v.findViewById(R.id.gridview);
        recyclerView = v.findViewById(R.id.list_room_rv);
        rvManager = new LinearLayoutManager(getContext());

//        gridview.setAdapter(new GridAdapter(this.getActivity(),));
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

        mCompositeDisposable.add(HostService.getInstance().getTodayList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setRooms));

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setRooms(@NonNull final List<MeetingDetail> Rooms) {

        recyclerView.setLayoutManager(rvManager);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        List<MeetingDetail> rooms = new ArrayList<>();
        for(int i =0;i<Rooms.size();i++){
            if(Rooms.get(i).getActivity_seq()==6)
                rooms.add(Rooms.get(i));
        }
        recyclerView.setAdapter(new MeetingListAdapter(rooms,getContext(),this));

    }




//    private void setActivites(@NonNull final List<Activity> languages) {
//        assert mLanguagesSpinner != null;
//
//        mLanguageSpinnerAdapter = new LanguageSpinnerAdapter(this,
//                R.layout.language_item,
//                languages);
//        mLanguagesSpinner.setAdapter(mLanguageSpinnerAdapter);
//    }


    @NonNull
    private MeetingListInnerViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getEtcListViewModel();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bind();
    }
}
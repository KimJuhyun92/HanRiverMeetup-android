package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab3Adapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab3ViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.TestFragment;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.service.MyPageService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Tab3 extends Fragment {

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private Tab3ViewModel mTab3ViewModel;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Tab3VO> mItems = new ArrayList<>();

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTab3ViewModel = getViewModel();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.tab3_fragment, container, false);
        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.tab3_fragment, container, false);
        setupViews(v);

        Tab3Adapter tab3Adapter = new Tab3Adapter(getActivity(),mItems);
        mRecyclerView.setAdapter(tab3Adapter);

        return v;
    }

    private void setupViews(View v){
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // use a linear layout manager 
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
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

//        mCompositeDisposable.add(mTab3ViewModel.getAvailableTab3VOs()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::setTab3VOs));

        mCompositeDisposable.add(MyPageService.getInstance().getMathcedMeeting(LoginFragment.getUser_id())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTab3VOs));
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setTab3VOs(@NonNull final List<Tab3VO> tab3VOs) {
        mRecyclerView.setAdapter(new Tab3Adapter(getActivity(),tab3VOs));
    }

    @NonNull
    private Tab3ViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getTab3ViewModel();
    }
}
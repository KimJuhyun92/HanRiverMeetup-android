package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab1Adapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab1ViewModel;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Tab1 extends Fragment{

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private Tab1ViewModel mTab1ViewModel;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    public Tab1() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTab1ViewModel = getViewModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.tab1_fragment, container, false);
        setupViews(v);


        return v;
    }


    private void setupViews(View v){
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

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

        mCompositeDisposable.add(mTab1ViewModel.getAvailableTab1VOs()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTab1VOs));
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setTab1VOs(@NonNull final List<Tab1VO> tab1VOs) {
//        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setAdapter(new Tab1Adapter(getActivity(),tab1VOs));
    }

    @NonNull
    private Tab1ViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getTab1ViewModel();
    }
}

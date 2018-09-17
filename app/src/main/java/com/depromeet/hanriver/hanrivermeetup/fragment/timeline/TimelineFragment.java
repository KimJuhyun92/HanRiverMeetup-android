package com.depromeet.hanriver.hanrivermeetup.fragment.timeline;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.Adapter.TimeLineAdapter;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.TimeLineVO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {

    Button btn;
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private TimelineViewModel mViewModel;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public TimelineFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = getViewModel();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceSatate) {

        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_timeline, container, false);
        setupViews(v);
//        mRecyclerView = v.findViewById(R.id.recyclerview);
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager 
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
//        myDataset = new ArrayList<>();
//        mAdapter = new TimeLineAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);

//        myDataset.add(new TimeLineVO("test1", R.mipmap.ic_launcher));
//        myDataset.add(new TimeLineVO("test2", R.mipmap.ic_launcher_round));
//        myDataset.add(new TimeLineVO("test3", R.mipmap.ic_launcher_round));

        return v;
    }

    private void setupViews(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager 
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


//        btn= v.findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                TestFragment test = TestFragment.newInstance();
//                ft.replace(R.id.root_frame,new TestFragment());
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
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

        mCompositeDisposable.add(mViewModel.getAvailableTimelineVOs()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTimeLines));
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setTimeLines(@NonNull final List<TimeLineVO> timeLines) {
        mRecyclerView.setAdapter(new TimeLineAdapter(getActivity(),timeLines));
    }

    @NonNull
    private TimelineViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getTimelineViewModel();
    }


}

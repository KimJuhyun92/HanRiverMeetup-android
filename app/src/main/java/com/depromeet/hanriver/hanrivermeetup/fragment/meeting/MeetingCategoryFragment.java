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

import com.depromeet.hanriver.hanrivermeetup.Activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Category.MeetingCategoryAdapter;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MeetingCategoryFragment extends Fragment {

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private MeetingCategoryViewModel mViewModel;

    @Nullable
    private TextView mActivitesView;
    private GridView gridview;
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
        View v = inflater.inflate(R.layout.fragment_meeting_category, container, false);
        setupViews(v);
        Log.d("TAG","oncreateView");
        return v;

    }

    private void setupViews(View v) {
        mActivitesView = v.findViewById(R.id.test_text);
//        gridview = v.findViewById(R.id.gridview);
        recyclerView = v.findViewById(R.id.category_rv);
        rvManager = new LinearLayoutManager(getContext());
        Log.d("TAG","setupViews");
        MainActivity.tabVisible(View.VISIBLE);

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

        mCompositeDisposable.add(mViewModel.getAvailableActivites()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setActivites));

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setActivites(@NonNull final List<Activity> activites) {
        assert mActivitesView != null;
//        gridview.setAdapter(new GridAdapter(getActivity(),activites));
        recyclerView.setLayoutManager(rvManager);
        recyclerView.setAdapter(new MeetingCategoryAdapter(activites,getContext(),this));

//        TestFrag frag = new TestFrag();
//        FragmentManager fragmentManager = getFragmentManager();


//        mActivitesView.setText("한강에서\n" +
//                "원하는 모임을 선택하세요");
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
    private MeetingCategoryViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getMeetingCategoryViewModel();
    }
}

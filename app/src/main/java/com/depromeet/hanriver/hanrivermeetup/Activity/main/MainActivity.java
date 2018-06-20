package com.depromeet.hanriver.hanrivermeetup.Activity.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private MainViewModel mViewModel;

    @Nullable
    private TextView mActivitesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = getViewModel();
        setupViews();
    }

    private void setupViews() {
        mActivitesView = (TextView)findViewById(R.id.activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    @Override
    protected void onPause() {
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

        mActivitesView.setText("hello");
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
    private MainViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getApplication()).getMainViewModel();
    }
}

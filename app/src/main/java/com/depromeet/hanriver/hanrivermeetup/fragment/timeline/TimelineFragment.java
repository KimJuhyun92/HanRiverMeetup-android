package com.depromeet.hanriver.hanrivermeetup.fragment.timeline;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.Adapter.TimeLineAdapter;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.TimeLineVO;
import com.depromeet.hanriver.hanrivermeetup.service.TimelineService;

import org.reactivestreams.Publisher;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;

public class TimelineFragment extends Fragment {

    private PublishProcessor<Integer> paginator = PublishProcessor.create();

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TimeLineAdapter mTimeLineAdapter;

    private boolean loading = false;
    private int pageNumber = 0;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;

    public TimelineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceSatate) {
        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_timeline, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mTimeLineAdapter = new TimeLineAdapter(getActivity());
        mRecyclerView.setAdapter(mTimeLineAdapter);
        setUpLoadMoreListener();
        //bind();
        return v;
    }

    private void setUpLoadMoreListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = mLayoutManager.getItemCount();
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                if (!loading
                        && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    pageNumber++;
                    paginator.onNext(pageNumber);
                    loading = true;
                }
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

    private Flowable<List<TimeLineVO>> dataFromNetwork(final int page) {
        return Flowable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(value -> {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = new Date();
                    try {
                        date = format.parse("2018-09-07");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return TimelineService.getInstance().getPosts(
                            date,
                            pageNumber * 2,
                            2).blockingSingle();
                });
    }

    private void bind() {
        pageNumber = 0;
        mTimeLineAdapter.clear();
        mTimeLineAdapter.notifyDataSetChanged();

        Disposable disposable = paginator
                .onBackpressureDrop()
                .concatMap(new Function<Integer, Publisher<List<TimeLineVO>>>() {
                    @Override
                    public Publisher<List<TimeLineVO>> apply(@io.reactivex.annotations.NonNull Integer page) {
                        loading = true;
                        return dataFromNetwork(page);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TimeLineVO>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<TimeLineVO> items) {
                        mTimeLineAdapter.addItems(items);
                        mTimeLineAdapter.notifyDataSetChanged();
                        loading = false;
                    }
                });

        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
        paginator.onNext(pageNumber);
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }
}

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
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Category.MeetingCategoryAdapter;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Weather;
import com.depromeet.hanriver.hanrivermeetup.service.WeatherService;

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
    private ImageView weather_img;
    private TextView weather_temp,weather_temp_sub;

    private final String[] skyState = {"맑음","구름조금","구름많음","흐림"};
    private final int[] skyState_img = {R.drawable.ic_weather_sunny,R.drawable.ic_weather_alittlecloudy,R.drawable.ic_weather_muchcloudy,R.drawable.ic_weather_fog};
    private final int[] rainState_img = {0,R.drawable.ic_weather_rain,R.drawable.ic_weather_rain_snow,R.drawable.ic_weather_snow};

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
        weather_img = v.findViewById(R.id.weather_img);
        weather_temp = v.findViewById(R.id.weather_temp);
        weather_temp_sub = v.findViewById(R.id.weather_temp_sub);
        mActivitesView = v.findViewById(R.id.category_main_text);
        mActivitesView.setText(LoginFragment.getNick_name()+" 님\n한강에서 즐겨볼까요?");
//        gridview = v.findViewById(R.id.gridview);
        recyclerView = v.findViewById(R.id.category_rv);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
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

        mCompositeDisposable.add(WeatherService.getInstance().getWeather()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setWeather));

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

    private void setWeather(@NonNull final Weather weather){
        weather_temp.setText(weather.getT1h().toString());
        weather_temp_sub.setText(skyState[Integer.parseInt(weather.getSky())]+"\n" + weather.getTmn()+"℃ / " + weather.getTmx()+"℃");
        if (Integer.parseInt(weather.getPty()) != 0) {
            weather_img.setImageResource(rainState_img[Integer.parseInt(weather.getPty())]);
        }
        else{
            weather_img.setImageResource(skyState_img[Integer.parseInt(weather.getSky())]);
        }
    }
//    맑음\n20℃ / 32℃


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

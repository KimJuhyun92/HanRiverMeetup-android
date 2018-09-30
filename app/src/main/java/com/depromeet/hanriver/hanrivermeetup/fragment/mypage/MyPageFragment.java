package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab3Adapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab3ViewModel;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.AlarmDetail;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;
import com.depromeet.hanriver.hanrivermeetup.service.MyPageService;
import com.depromeet.hanriver.hanrivermeetup.service.NotificationService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MyPageFragment extends Fragment{

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private TabLayout tabLayout;

    @NonNull
    private CustomViewpager viewPager;

    List<Tab1VO> tab1VOList = new ArrayList<Tab1VO>();
    List<Tab2VO> tab2VOList = new ArrayList<Tab2VO>();
    List<Tab3VO> tab3VOList = new ArrayList<Tab3VO>();
    List<AlarmDetail> alarmDetails = new ArrayList<>();

    private ImageView profile_img;
    private TextView user_name;
    private TextView tab1_tab;
    private TextView tab2_tab;
    private TextView tab3_tab;
    private TextView main_text;
    private ScrollView scrollView;
    private ImageButton alarmButton;
    private ImageButton logoutButton;
    private View tabView1;
    private View tabView2;
    private View tabView3;
    private Dialog logoutDialog;
    private Dialog alarmDialog;
    private Typeface normalFont,boldFont;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_mypage, container, false);

        normalFont = ResourcesCompat.getFont(getContext(),R.font.nanumsquareregular);
        boldFont = ResourcesCompat.getFont(getContext(),R.font.nanumsquarebold);

        scrollView = (ScrollView)view.findViewById(R.id.mypage_scroll);
        scrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);


        main_text = view.findViewById(R.id.mypage_main_text);
        main_text.setText("안녕하세요\n" + PreferencesManager.getNickname() + " 님 반가워요");

        // ImageButton
        alarmButton = view.findViewById(R.id.alarm_btn);
        logoutButton = view.findViewById(R.id.logout_btn);

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCompositeDisposable = new CompositeDisposable();

                mCompositeDisposable.add(NotificationService.getInstance()
                        .getNotificationLog(PreferencesManager.getUserID())
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::setNotificationLog));
            }

            private void setNotificationLog(List<AlarmDetail> alarmDetails) {
                alarmDialog = new AlarmDialog(view.getContext(), alarmDetails);
                alarmDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                alarmDialog.show();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity)getActivity();
                logoutDialog = new LogoutDialog(view.getContext(), mainActivity);
                logoutDialog.show();
            }
        });

        // Initializing the TabLayout
        tabLayout = view.findViewById(R.id.tablayout2);
        tabLayout.setTabRippleColor(null);


        // Custom tablayout

        tabView1 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab1_layout, null);
        tab1_tab = tabView1.findViewById(R.id.tab1_tab);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tabView1));

        tabView2 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab2_layout, null);
        tab2_tab = tabView2.findViewById(R.id.tab2_tab);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tabView2));

        tabView3 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab3_layout, null);
        tab3_tab = tabView3.findViewById(R.id.tab3_tab);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tabView3));

        initTablayoutWeight(tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = view.findViewById(R.id.viewpager2);

        // Creating TabPagerAdapter adapter
        MyPageTabAdapter pagerAdapter = new MyPageTabAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Profile Setting
        profile_img = (ImageView)view.findViewById(R.id.profile_img);
        Picasso.get().load(FacebookService.getInstance()
                .getProfileURL(PreferencesManager.getUserID()))
                .transform(CircleTransform.getInstance()).into(profile_img);

        user_name = (TextView)view.findViewById(R.id.user_name);
        user_name.setText(PreferencesManager.getNickname());

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                bind();
                if(tab.getPosition()==0){
                    tab1_tab.setTypeface(boldFont);
                    tab1_tab.setTextColor(Color.parseColor("#2186f8"));
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#2186f8"));
                }
                else if(tab.getPosition()==1){
                    tab2_tab.setTypeface(boldFont);
                    tab2_tab.setTextColor(Color.parseColor("#00c0c9"));
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00c0c9"));

                }
                else if(tab.getPosition()==2){
                    tab3_tab.setTypeface(boldFont);
                    tab3_tab.setTextColor(Color.parseColor("#333333"));
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#333333"));

                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    tab1_tab.setTypeface(normalFont);
                    tab1_tab.setTextColor(Color.parseColor("#949494"));
                }
                else if(tab.getPosition()==1){
                    tab2_tab.setTypeface(normalFont);
                    tab2_tab.setTextColor(Color.parseColor("#949494"));

                }
                else if(tab.getPosition()==2){
                    tab3_tab.setTypeface(normalFont);
                    tab3_tab.setTextColor(Color.parseColor("#949494"));

                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
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

        mCompositeDisposable.add(MyPageService.getInstance()
                .getMyMeeting(PreferencesManager.getUserID())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTab1Count));

        mCompositeDisposable.add(MyPageService.getInstance()
                .getAppliedMeeting(PreferencesManager.getUserID())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTab2Count));

        mCompositeDisposable.add(MyPageService.getInstance()
                .getMathcedMeeting(PreferencesManager.getUserID())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTab3Count));
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setTab1Count(@NonNull final List<Tab1VO> tab1VOs) {
        tab1VOList = tab1VOs;
    }

    private void setTab2Count(@NonNull final List<Tab2VO> tab2VOs) {
        tab2VOList = tab2VOs;
    }

    private void setTab3Count(@NonNull final List<Tab3VO> tab3VOs) {
        tab3VOList = tab3VOs;
    }

    @NonNull
    private Tab3ViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getTab3ViewModel();
    }

    private void initTablayoutWeight(TabLayout tablayout){
        LinearLayout linearLayout = (LinearLayout)tabLayout.getChildAt(0);
        for(int i = 0; i<3; i++){
            View vv = linearLayout.getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) vv.getLayoutParams();
            params.weight = 0;
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.leftMargin = 70;
            params.rightMargin = 70;
            vv.setLayoutParams(params);
        }
    }


}

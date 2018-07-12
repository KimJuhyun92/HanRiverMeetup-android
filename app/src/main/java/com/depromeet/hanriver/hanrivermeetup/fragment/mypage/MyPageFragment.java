package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.depromeet.hanriver.hanrivermeetup.R;

public class MyPageFragment extends Fragment{

    @NonNull
    private TabLayout tabLayout;

    @NonNull
    private ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_mypage, container, false);

        // Initializing the TabLayout
        tabLayout = view.findViewById(R.id.tablayout2);
        tabLayout.addTab(tabLayout.newTab().setText("test1"));
        tabLayout.addTab(tabLayout.newTab().setText("test2"));
        tabLayout.addTab(tabLayout.newTab().setText("test3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = view.findViewById(R.id.viewpager2);

        // Creating TabPagerAdapter adapter
        MyPageTabAdapter pagerAdapter = new MyPageTabAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}

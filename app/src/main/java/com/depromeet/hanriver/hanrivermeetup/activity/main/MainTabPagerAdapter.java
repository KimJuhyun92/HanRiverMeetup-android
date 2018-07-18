package com.depromeet.hanriver.hanrivermeetup.activity.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.meetingRootFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.MyPageFragment;

public class MainTabPagerAdapter extends FragmentStatePagerAdapter {
    // Count number of tabs
    private int tabCount;

    public MainTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                return new meetingRootFragment();
//                return new LoginFragment();
//                return null;
            case 1:
                return new MyPageFragment();
//                return new LoginFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

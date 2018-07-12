package com.depromeet.hanriver.hanrivermeetup.Activity.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCategoryFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.meetingRootFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.TimelineFragment;

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
            case 1:
                return new TimelineFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

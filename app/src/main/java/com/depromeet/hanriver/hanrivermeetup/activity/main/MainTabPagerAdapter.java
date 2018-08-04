package com.depromeet.hanriver.hanrivermeetup.activity.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MapFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingDetailFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.meetingRootFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.MyPageFragment;

public class MainTabPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public MainTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new meetingRootFragment();
            case 1:
                return new MyPageFragment();
            case 2:
                return new MapFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

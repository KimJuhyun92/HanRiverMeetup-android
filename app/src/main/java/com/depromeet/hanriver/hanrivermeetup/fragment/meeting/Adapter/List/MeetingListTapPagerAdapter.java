package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingListInnerFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.meetingRootFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.MyPageFragment;

public class MeetingListTapPagerAdapter extends FragmentStatePagerAdapter {
    // Count number of tabs
    private int tabCount;

    public MeetingListTapPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                return new MeetingListInnerFragment();
//                return new meetingRootFragment();
            case 1:
                return new MeetingListInnerFragment();
//                return new MyPageFragment();
//                return new LoginFragment();
            case 2:
                return new MeetingListInnerFragment();
            case 3:
                return new MeetingListInnerFragment();
            case 4:
                return new MeetingListInnerFragment();
            case 5:
                return new MeetingListInnerFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
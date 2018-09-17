package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment.BikeFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment.BoatFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment.CampingFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment.ChickenFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment.EtcFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.CategoryListFragment.PhotoFragment;
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
                return ChickenFragment.newInstance(position+1);
            case 1:
                return BikeFragment.newInstance(position+1);
            case 2:
                return BoatFragment.newInstance(position+1);
            case 3:
                return CampingFragment.newInstance(position+1);
            case 4:
                return PhotoFragment.newInstance(position+1);
            case 5:
                return EtcFragment.newInstance(position+1);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
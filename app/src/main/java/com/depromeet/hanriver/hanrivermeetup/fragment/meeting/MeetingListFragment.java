package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depromeet.hanriver.hanrivermeetup.activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List.MeetingListTapPagerAdapter;

public class MeetingListFragment extends Fragment{

    private FloatingActionButton fab;
    private ViewPager viewpager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static MeetingListFragment newInstance() {

        Bundle args = new Bundle();
        MeetingListFragment fragment = new MeetingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void setupViews(View v) {
        fab = v.findViewById(R.id.meeting_list_fab);
        fab.setOnClickListener(mClick);
        MainActivity.tabVisible(View.GONE);
        viewpager = v.findViewById(R.id.meeting_list_vp);
        Log.d("TAG","setupViews");
        tabLayout = v.findViewById(R.id.list_tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("치킨"));
        tabLayout.addTab(tabLayout.newTab().setText("자전거"));
        tabLayout.addTab(tabLayout.newTab().setText("오리배"));
        tabLayout.addTab(tabLayout.newTab().setText("캠핑"));
        tabLayout.addTab(tabLayout.newTab().setText("사진"));
        tabLayout.addTab(tabLayout.newTab().setText("기타"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        MeetingListTapPagerAdapter adapter = new MeetingListTapPagerAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        setupViews(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
////        TestFrag frag = new TestFrag();
//            MeetingDetailFragment frag = MeetingDetailFragment.newInstance();
//            fragTransaction.replace(R.id.meeting_root, frag);
//            fragTransaction.addToBackStack(null);
//            fragTransaction.commit();
            MeetingCreateRoom dialog = MeetingCreateRoom.newInstance();
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
            dialog.show(getFragmentManager(), "tag");

        }
    };

}

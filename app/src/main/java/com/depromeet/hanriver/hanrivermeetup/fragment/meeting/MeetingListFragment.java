package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.activity.main.MainActivity;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List.MeetingListTapPagerAdapter;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;
import com.github.clans.fab.FloatingActionButton;

import java.sql.Time;

public class MeetingListFragment extends Fragment {

    private FloatingActionButton fab;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    public static int current_position;
    ImageButton back_btn;
    ImageView category_img;
    static int image_num[];
    MeetingListFragment frag;
    View tabs[];
    TextView tabname[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frag = this;
        tabs = new View[6];
        tabname = new TextView[6];
        image_num = new int[6];
        image_num[0] = R.drawable.ic_chicken_white;
        image_num[1] = R.drawable.ic_bike_icon_white;
        image_num[2] = R.drawable.ic_ori_icon_white;
        image_num[3] = R.drawable.ic_camping_icon_white;
        image_num[4] = R.drawable.ic_photo_icon_white;
        image_num[5] = R.drawable.ic_etc_icon_white;
    }

    public static MeetingListFragment newInstance(int position) {
        current_position = position;
        Log.d("position:", "" + current_position);
        Bundle args = new Bundle();
        MeetingListFragment fragment = new MeetingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void setupViews(View v) {
        back_btn = v.findViewById(R.id.meeting_list_back);
        back_btn.setOnClickListener(back_click);
        category_img = v.findViewById(R.id.list_category_img);
        category_img.setImageResource(image_num[current_position]);
        fab = v.findViewById(R.id.meeting_list_fab);
        fab.setOnClickListener(mClick);
        MainActivity.tabVisible(View.GONE);
        viewpager = v.findViewById(R.id.meeting_list_vp);
        viewpager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        Log.d("TAG", "setupViews");
        tabLayout = v.findViewById(R.id.list_tablayout);

        for (int i = 0; i < 6; i++) {
            tabs[i] = getLayoutInflater().inflate(R.layout.tab_meeting_list, null);
            tabname[i] = tabs[i].findViewById(R.id.meeting_list_tab_name);

            tabLayout.addTab(tabLayout.newTab().setCustomView(tabs[i]));
        }
        tabname[0].setText("치킨");
        tabname[1].setText("자전거");
        tabname[2].setText("오리배");
        tabname[3].setText("캠핑");
        tabname[4].setText("사진");
        tabname[5].setText("기타");
        tabname[current_position].setTypeface(null,Typeface.BOLD);//선택되어 들어온 아이템을 볼드체로 변경.

        tabLayout.setOverScrollMode(View.OVER_SCROLL_NEVER);
        tabLayout.setTabRippleColor(null);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        MeetingListTapPagerAdapter adapter = new MeetingListTapPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                current_position = tab.getPosition();
                category_img.setImageResource(image_num[current_position]);
                tabname[tab.getPosition()].setTypeface(null,Typeface.BOLD);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabname[tab.getPosition()].setTypeface(null,Typeface.NORMAL);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewpager.setCurrentItem(current_position);

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
            MeetingCreateRoom dialog = MeetingCreateRoom.newInstance(current_position + 1, frag);
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
            dialog.show(getFragmentManager(), "tag");

        }
    };

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    };
}

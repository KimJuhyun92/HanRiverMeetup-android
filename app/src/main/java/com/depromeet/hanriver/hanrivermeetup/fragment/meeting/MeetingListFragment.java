package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

import java.lang.reflect.Type;
import java.sql.Time;

public class MeetingListFragment extends Fragment {

    private FloatingActionButton fab;
    private FrameLayout listTop;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    public static int current_position;
    ImageButton back_btn;
    ImageView category_img;
    static int image_num[];
    MeetingListFragment frag;
    View tabs[];
    TextView tabname[];
    Typeface normalFont,boldFont;


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

        normalFont = ResourcesCompat.getFont(getContext(),R.font.nanumsquareregular);
        boldFont = ResourcesCompat.getFont(getContext(),R.font.nanumsquarebold);

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
        listTop = v.findViewById(R.id.list_top);
        back_btn = v.findViewById(R.id.meeting_list_back);
        back_btn.setOnClickListener(back_click);
        category_img = v.findViewById(R.id.list_category_img);
        category_img.setImageResource(image_num[current_position]);
        fab = v.findViewById(R.id.meeting_list_fab);
        fab.setOnClickListener(mClick);
//        MainActivity.tabVisible(View.GONE);
        viewpager = v.findViewById(R.id.meeting_list_vp);
        viewpager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        Log.d("TAG", "setupViews");
        tabLayout = v.findViewById(R.id.list_tablayout);

        /////////////////////////////////////////////////////

        gradationOnListTop(listTop);

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

        tabname[current_position].setTypeface(boldFont);//선택되어 들어온 아이템을 볼드체로 변경.

        tabLayout.setOverScrollMode(View.OVER_SCROLL_NEVER);
        tabLayout.setTabRippleColor(null);

        initTablayoutWeight(tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        MeetingListTapPagerAdapter adapter = new MeetingListTapPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                current_position = tab.getPosition();
                category_img.setImageResource(image_num[current_position]);
                tabname[tab.getPosition()].setTypeface(boldFont);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabname[tab.getPosition()].setTypeface(normalFont);

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

            MeetingCreateRoom dialog = MeetingCreateRoom.newInstance(current_position + 1, frag);
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
            dialog.show(getFragmentManager(), "tag");

        }
    };

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    };

    private void initTablayoutWeight(TabLayout tablayout){
        LinearLayout linearLayout = (LinearLayout)tablayout.getChildAt(0);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int diff =  display.getWidth()-(int)(tabname[0].getTextSize()*14); //최대 가로 크기 - 6개 탭의 크기
        diff = diff/12;
        for(int i = 0; i<linearLayout.getChildCount(); i++){
            View vv = linearLayout.getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) vv.getLayoutParams();
            params.weight = 0;
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.leftMargin = diff;
            params.rightMargin = diff;
            vv.setLayoutParams(params);
        }
    }

    private void gradationOnListTop(View view){
        ShapeDrawable.ShaderFactory sf = new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                LinearGradient lg = new LinearGradient(0, 0, 0, height,
                        // 그라데이션 색상이 들어가는 배열.
                        new int[]{Color.parseColor("#1A75F0"),Color.parseColor("#1B70F3"),Color.parseColor("#1A7AEB"),Color.parseColor("#1985E1"),Color.parseColor("#178FDA"),Color.parseColor("#18B1DA")},
//                        new int[]{Color.parseColor("#18B1DA"),Color.parseColor("#000000")},
                        // 각 색상별 포지션 지정하는 배열. 최소값은 0이고 최대값을 1이다.
                        new float[]{0,0.2f,0.4f,0.6f,0.8f,1},
//                        new float[]{0,1},
                        // 뷰의 크기에 따라서 적용될 것이기 때문에 뭘 지정해도 큰 차이가 없다.
                        Shader.TileMode.REPEAT);
                return lg;
            }
        };
        PaintDrawable pd = new PaintDrawable();
        pd.setShape(new RectShape());
        pd.setShaderFactory(sf);

// PaintDrawable 객체를 뷰에 적용
        view.setBackground(pd);
    }
}

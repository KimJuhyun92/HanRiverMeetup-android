package com.depromeet.hanriver.hanrivermeetup.activity.main;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @NonNull
    private static TabLayout tabLayout;

    @NonNull
    private ViewPager viewPager;
    ImageView nolgang_img;
    TextView nolgang_text;
    ImageView mypage_img;
    TextView mypage_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permission Check
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("모임 연결을 위해선 전화 권한이 필요합니다.")
                .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요!")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        // Initializing the TabLayout
        tabLayout = findViewById(R.id.tablayout);
//        tabLayout.addTab(tabLayout.newTab().setText("같이놀강"));
        View nolgang_item = getLayoutInflater().inflate(R.layout.tab_icon_nolgang, null);
        nolgang_img = nolgang_item.findViewById(R.id.nolgang_img);
        nolgang_text = nolgang_item.findViewById(R.id.nolgang_text);
        View mypage_item = getLayoutInflater().inflate(R.layout.tab_icon_mypage, null);
        mypage_img = mypage_item.findViewById(R.id.mypage_img);
        mypage_text = mypage_item.findViewById(R.id.mypage_text);

        tabLayout.addTab(tabLayout.newTab().setCustomView(nolgang_item));
        tabLayout.addTab(tabLayout.newTab().setCustomView(mypage_item));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Initializing ViewPager
        viewPager = findViewById(R.id.viewpager);
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        // Creating TabPagerAdapter adapter
        com.depromeet.hanriver.hanrivermeetup.activity.main.MainTabPagerAdapter pagerAdapter = new com.depromeet.hanriver.hanrivermeetup.activity.main.MainTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0) {
                    nolgang_img.setImageResource(R.drawable.ic_nolgang_icon_active);
                    nolgang_text.setTextColor(Color.parseColor("#2186f8"));
                }
                if(tab.getPosition()==1) {
                    mypage_img.setImageResource(R.drawable.ic_mypage_icon_active);
                    mypage_text.setTextColor(Color.parseColor("#2186f8"));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    nolgang_img.setImageResource(R.drawable.ic_nolgang_icon);
                    nolgang_text.setTextColor(Color.parseColor("#000000"));
                }
                if(tab.getPosition()==1) {
                    mypage_img.setImageResource(R.drawable.ic_mypage_icon);
                    mypage_text.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static void tabVisible(int visible){
        tabLayout.setVisibility(visible);
    }



}

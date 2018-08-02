package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.FaceDetector;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab3Adapter;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.depromeet.hanriver.hanrivermeetup.service.HostService;
import com.depromeet.hanriver.hanrivermeetup.service.MyPageService;
import com.facebook.Profile;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MyPageFragment extends Fragment{

    private Handler handler = new Handler();
    private ImageView profile_img;

    @NonNull
    private TabLayout tabLayout;

    @NonNull
    private CustomViewpager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_mypage, container, false);

        // Initializing the TabLayout
        tabLayout = view.findViewById(R.id.tablayout2);


        // Custom tablayout
        View tabView1 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab1_layout, null, false);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tabView1));

        View tabView2 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab2_layout, null, false);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tabView2));

        View tabView3 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab3_layout, null, false);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tabView3));

//        tabLayout.addTab(tabLayout.newTab().setText("내가 만든 모임"));
//        tabLayout.addTab(tabLayout.newTab().setText("내가 신청한 모임"));
//        tabLayout.addTab(tabLayout.newTab().setText("매칭된 모임"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Initializing ViewPager
        viewPager = view.findViewById(R.id.viewpager2);

        // Creating TabPagerAdapter adapter
        MyPageTabAdapter pagerAdapter = new MyPageTabAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

//        비동기 방식으로 프로필 받아오기
        new AsyncTask<Void,Void,Bitmap>(){

            @Override
            protected Bitmap doInBackground(Void... voids) {

                Bitmap bitmap ;
                bitmap = FacebookService.getInstance().getProfileURI("1320458764757184");

                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                profile_img = (ImageView)view.findViewById(R.id.profile_img);
                profile_img.setImageBitmap(bitmap);
            }
        }.execute();


//        // Thread 사용하여 프로필 이미지 받아오기.
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    URL url = new URL("https://graph.facebook.com/1320458764757184/picture?type=large");
//                    InputStream is =url.openStream();
//                    Bitmap bitmap = BitmapFactory.decodeStream(is);
//
//                    //핸들러 사용
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            profile_img = (ImageView)view.findViewById(R.id.profile_img);
//                            profile_img.setImageBitmap(bitmap);
//                        }
//                    });
//                    profile_img.setImageBitmap(bitmap);
//                }catch (Exception e){
//                    e.printStackTrace(); }
//            }
//        });
//
//        thread.start();



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

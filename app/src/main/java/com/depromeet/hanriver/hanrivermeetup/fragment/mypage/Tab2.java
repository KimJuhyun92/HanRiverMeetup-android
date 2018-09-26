package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab2Adapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab3Adapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab3ViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.TestFragment;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.service.GuestService;
import com.depromeet.hanriver.hanrivermeetup.service.MyPageService;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Tab2 extends Fragment {

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    SwipeMenuListView swipeMenuListView;
    List<Tab2VO> testVO = new ArrayList<Tab2VO>();
    Tab2Adapter tab2Adapter;

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);

        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.swipe_menu_listView);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#00c0c9")));
                // set item width
                deleteItem.setWidth(400);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_negative_icon);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        swipeMenuListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener((position, menu, index) -> {
            switch (index) {
                case 0:
                    GuestService.getInstance().deleteJoinRequest(testVO.get(position).getApplicationSeq())
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Toast.makeText(getActivity(), "삭제 성공!", Toast.LENGTH_SHORT).show();
                                    bind();
                                }
                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getActivity(), "삭제 실패!", Toast.LENGTH_SHORT).show();
                                }
                            });

                    break;
//                    case 1:
//                        break;
            }
            // false : close the menu; true : not close the menu
            return false;
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        bind();
    }

    @Override
    public void onPause() {
        super.onPause();
        unBind();
    }

    private void bind() {
        mCompositeDisposable = new CompositeDisposable();

        mCompositeDisposable.add(MyPageService.getInstance()
                .getAppliedMeeting(PreferencesManager.getUserID())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTab2VOs));
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setTab2VOs(@NonNull final List<Tab2VO> tab2VOs) {
        testVO = tab2VOs;
        swipeMenuListView.setAdapter(tab2Adapter = new Tab2Adapter(tab2VOs));
//        tab2Adapter.notifyDataSetChanged();
    }

    @NonNull
    private Tab3ViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getTab3ViewModel();
    }
}

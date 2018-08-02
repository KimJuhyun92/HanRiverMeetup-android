package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab2Adapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab3Adapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.ViewModel.Tab3ViewModel;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.TestFragment;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;
import com.depromeet.hanriver.hanrivermeetup.service.MyPageService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Tab2 extends Fragment {

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    SwipeMenuListView listView;

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

        listView = (SwipeMenuListView) view.findViewById(R.id.listView);

//        Tab2Adapter adapter = new Tab2Adapter(test);
//        listView.setAdapter(adapter);



        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#2186F8")));
                // set item width
                deleteItem.setWidth(400);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_negative_icon);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        //삭제버튼 클릭시 통신
                        Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
                        




                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        return view;
    }

    private void setupViews(View v){
        listView = v.findViewById(R.id.recyclerview);
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

//        mCompositeDisposable.add(mTab3ViewModel.getAvailableTab3VOs()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::setTab3VOs));

        mCompositeDisposable.add(MyPageService.getInstance().getAppliedMeeting("1320458764757184")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTab2VOs));
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void setTab2VOs(@NonNull final List<Tab2VO> tab2VOs) {
        listView.setAdapter(new Tab2Adapter(tab2VOs));
    }

    @NonNull
    private Tab3ViewModel getViewModel() {
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getTab3ViewModel();
    }
}

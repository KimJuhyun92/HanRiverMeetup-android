package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab3Adapter;
import com.depromeet.hanriver.hanrivermeetup.fragment.timeline.TestFragment;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab3VO;

import java.util.ArrayList;

public class Tab3 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Tab3VO> mItems = new ArrayList<Tab3VO>();

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
//        View view = inflater.inflate(R.layout.tab3_fragment, container, false);
        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.tab3_fragment, container, false);

        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager 
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        Tab3Adapter tab3Adapter = new Tab3Adapter(getActivity(),mItems);
        mRecyclerView.setAdapter(tab3Adapter);

        mItems.add(new Tab3VO("test1","여의도 한강공원", "11시", 15000, 3));
        mItems.add(new Tab3VO("test2","여의도 한강공원", "11시", 15000, 3));
        mItems.add(new Tab3VO("test3","여의도 한강공원", "11시", 15000, 3));

        return v;
    }
}
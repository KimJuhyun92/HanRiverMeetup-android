package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.CreateRoom.LocationListAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CreateRoomLocationFragment extends DialogFragment{
    List<String> list;
    private RecyclerView rv;
    private RecyclerView.LayoutManager rvManager;
    TextView textview;

    public static CreateRoomLocationFragment newInstance(TextView textView) {

        Bundle args = new Bundle();

        CreateRoomLocationFragment fragment = new CreateRoomLocationFragment();
        fragment.setArguments(args);
        fragment.textview = textView;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meeting_create_location, container, false);
        setupViews(v);
        return v;
    }

    private void setupViews(View v) {
        rv = v.findViewById(R.id.meeting_create_location_rv);
        rvManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(rvManager);
        list = new ArrayList<>();
        list.add("여의도 한강공원");
        list.add("뚝섬 한강공원");
        list.add("반포 한강공원");
        list.add("이촌 한강공원");
        list.add("잠원 한강공원");
        rv.setAdapter(new LocationListAdapter(list,getContext(),this,textview));

    }
}

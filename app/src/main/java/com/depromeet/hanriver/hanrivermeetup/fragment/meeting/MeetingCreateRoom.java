package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.depromeet.hanriver.hanrivermeetup.R;

public class MeetingCreateRoom extends DialogFragment{
    RecyclerView rv;
    EditText roomname,roomcontent;
    ImageButton profileimg;
    Button createbtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static MeetingCreateRoom newInstance() {

        Bundle args = new Bundle();
        MeetingCreateRoom fragment = new MeetingCreateRoom();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meeting_createroom, container, false);
        setupViews(v);
        return v;
    }

    private void setupViews(View v) {
        roomname= v.findViewById(R.id.room_name);
        roomcontent= v.findViewById(R.id.room_content);
        profileimg = v.findViewById(R.id.profile_img);
        createbtn = v.findViewById(R.id.create_btn);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depromeet.hanriver.hanrivermeetup.R;

public class MeetingListFragment extends Fragment{

    FloatingActionButton fab;

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
        Log.d("TAG","setupViews");

//        gridview.setAdapter(new GridAdapter(this.getActivity(),));
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
//            MeetingCreateRoom frag = MeetingCreateRoom.newInstance();
//            fragTransaction.add(R.id.meeting_root, frag);
//            fragTransaction.addToBackStack(null);
//            fragTransaction.commit();
            MeetingCreateRoom dialog = MeetingCreateRoom.newInstance();
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
            dialog.show(getFragmentManager(), "tag");

        }
    };

}

package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depromeet.hanriver.hanrivermeetup.R;

public class meetingRootFragment extends Fragment {
    private static final String TAG = "meetingRootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.meetingroot, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        /*
         * When this container fragment is created, we fill it with our first
         * "real" fragment
         */
        transaction.replace(R.id.meeting_root, new MeetingCategoryFragment());

        transaction.commit();

        return view;
    }
}

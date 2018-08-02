package com.depromeet.hanriver.hanrivermeetup.fragment.meeting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.HanRiverMeetupApplication;
import com.depromeet.hanriver.hanrivermeetup.R;

import io.reactivex.disposables.CompositeDisposable;

public class MeetingJoinFragment extends DialogFragment{
    EditText numofMem,contact,reason;
    TextView textCounter;


    @NonNull
    private CompositeDisposable mCompositeDisposable;

    MeetingJoinViewModel meetingJoinViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static MeetingJoinFragment newInstance() {
        Bundle args = new Bundle();
        MeetingJoinFragment fragment = new MeetingJoinFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void setupViews(View v){
        numofMem = v.findViewById(R.id.join_participants_cnt);
        contact = v.findViewById(R.id.join_contact);
        reason = v. findViewById(R.id.join_reason);
        textCounter = v.findViewById(R.id.join_content_counter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meeting_join,container,false);
        setupViews(v);
        return v;
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

//        mCompositeDisposable.add(meetingDetailViewModel.getMeetingDetail(meeting_seq)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::setMeetingDetail));


    }

    private void unBind() {
        mCompositeDisposable.clear();
    }





    @NonNull
    private MeetingCommentViewModel getCommentViewModel(){
        return ((HanRiverMeetupApplication)getActivity().getApplicationContext()).getCommentViewModel();
    }
}

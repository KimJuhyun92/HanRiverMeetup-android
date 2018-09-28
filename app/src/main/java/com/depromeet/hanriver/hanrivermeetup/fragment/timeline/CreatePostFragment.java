package com.depromeet.hanriver.hanrivermeetup.fragment.timeline;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.CreateRoomLocationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePostFragment extends DialogFragment {
    @BindView(R.id.create_post_location) TextView locationTextView;
    @BindView(R.id.create_post_content) TextView contentTextView;

    @OnClick(R.id.create_post_back_btn)
    public void backFragment() {
        this.dismiss();
    }

    @OnClick(R.id.set_location_ll)
    public void setLocation() {
        CreateRoomLocationFragment dialog = CreateRoomLocationFragment.newInstance(locationTextView);
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
        dialog.show(getFragmentManager(), "tag");
    }

    @OnClick(R.id.create_post_btn)
    public void createPost() {
        this.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;

        getDialog().getWindow().setWindowAnimations(
                R.style.dialog_animation_move_to_up);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static CreatePostFragment newInstance() {
        CreatePostFragment fragment = new CreatePostFragment();
        return fragment;
    }
}

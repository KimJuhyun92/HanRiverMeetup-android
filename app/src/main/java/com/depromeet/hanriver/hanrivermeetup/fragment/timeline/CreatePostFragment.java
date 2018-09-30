package com.depromeet.hanriver.hanrivermeetup.fragment.timeline;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.CreateRoomLocationFragment;
import com.depromeet.hanriver.hanrivermeetup.network.AWSFileManager;

import java.io.File;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePostFragment extends DialogFragment {

    @BindView(R.id.createa_post_imageview) ImageView imgview;
    public String imgPath;
    private CreatePostFragment fragment;
    @BindView(R.id.create_post_location) TextView locationTextView;
    @BindView(R.id.create_post_content) TextView contentTextView;
    @BindView(R.id.add_image_btn) ImageButton imgbtn;

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
        File file = new File(imgPath);
        String fileName = UUID.randomUUID().toString();

        AWSMobileClient.getInstance().initialize(getContext()).execute();
        AWSFileManager.uploadImage(getContext(), fileName, file);
        this.dismiss();
    }

    @OnClick(R.id.add_image_btn)
    public void addImage(){
        ImageSheetDialogFragment imageSheetDialogFragment = ImageSheetDialogFragment.newInstance(imgview,this);
        imageSheetDialogFragment.show(getFragmentManager(),"ImageSheet");
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
        fragment = this;
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static CreatePostFragment newInstance() {
        CreatePostFragment fragment = new CreatePostFragment();
        return fragment;
    }
}

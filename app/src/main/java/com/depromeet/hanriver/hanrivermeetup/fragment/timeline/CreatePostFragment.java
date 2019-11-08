package com.depromeet.hanriver.hanrivermeetup.fragment.timeline;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.common.PreferencesManager;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils.CreateRoomLocationFragment;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.TimeLineVO;
import com.depromeet.hanriver.hanrivermeetup.network.AWSFileManager;
import com.depromeet.hanriver.hanrivermeetup.service.TimelineService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CreatePostFragment extends DialogFragment {
    public String imgPath;
    private static TimelineFragment parentFragment;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private CreatePostFragment fragment;

    @BindView(R.id.createa_post_imageview) ImageView imgview;
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

    @OnClick(R.id.add_image_btn)
    public void addImage(){
        ImageSheetDialogFragment imageSheetDialogFragment = ImageSheetDialogFragment.newInstance(imgview,this);
        imageSheetDialogFragment.show(getFragmentManager(),"ImageSheet");
    }

    @OnClick(R.id.create_post_btn)
    public void createPost() {
        File file = new File(imgPath);
        String fileName = UUID.randomUUID().toString();

        try {
            File compressedImageFile = new Compressor(getContext())
                    .setMaxWidth(640)
                    .setMaxHeight(480)
                    .compressToFile(file);

            AWSMobileClient.getInstance().initialize(getContext()).execute();
            AWSFileManager.uploadImage(parentFragment, getContext(), fileName, compressedImageFile);
            pushPost(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dismiss();
    }

    private void pushPost(String fileName) {
        mCompositeDisposable.add(TimelineService.getInstance().createPost(createPostData(fileName))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(res -> {
                    if (res.code() == HttpsURLConnection.HTTP_OK) {
                        this.dismiss();
                    } else {
                        Toast.makeText(getContext(), "포스팅 과정에서 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                        this.dismiss();
                    }
                })
                .subscribe());
    }

    private TimeLineVO createPostData(String fileName) {
        TimeLineVO post = new TimeLineVO();
        post.content = contentTextView.getText().toString();
        post.location = locationTextView.getText().toString();
        post.user_id = PreferencesManager.getUserID();
        post.nickname = PreferencesManager.getNickname();
//        post.imageurl = "https://s3.amazonaws.com/hanriver-userfiles-mobilehub-2110615695/posts/" + fileName;
        post.imageurl = "https://hanriverimage-deployments-mobilehub-125258651.s3.us-east-2.amazonaws.com/posts/" + fileName;

        return post;
    }

    private boolean isValidate() {
        return true;
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

    public static CreatePostFragment newInstance(TimelineFragment parentFragment) {
        CreatePostFragment fragment = new CreatePostFragment();
        fragment.parentFragment = parentFragment;

        return fragment;
    }
}

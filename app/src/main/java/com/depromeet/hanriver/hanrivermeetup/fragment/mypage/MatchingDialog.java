package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.login.LoginFragment;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MatchingDetail;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.ApplicantVO;
import com.depromeet.hanriver.hanrivermeetup.service.CommunicationService;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MatchingDialog extends Dialog {

    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ImageButton closeButton;
    private ImageButton okButton;
    private ImageButton cancelButton;


    public MatchingDialog(@NonNull Context context, ApplicantVO applicantVO) {
        super(context);

        Initialize();

        SetData(LoginFragment.getNick_name(),
                applicantVO.getNickname(),
                applicantVO.getUserId(),
                applicantVO.getParticipantsCnt(),
                applicantVO.getContact(),
                String.valueOf(applicantVO.getDescription()));

        closeButton = (ImageButton) findViewById(R.id.close_button);
        okButton = (ImageButton) findViewById(R.id.ok_button);
        cancelButton = (ImageButton) findViewById(R.id.cancel_button);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchingDetail matchingDetail = new MatchingDetail();

                matchingDetail.setRequestID(applicantVO.getApplicationSeq());
                matchingDetail.setMeetingID(applicantVO.getMeetingSeq());

                Gson gson = new Gson();
                String str  = gson.toJson(matchingDetail);

                Log.d("@@@@@",""+str);
                mCompositeDisposable.add(CommunicationService.getInstance().match(matchingDetail)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe());

                dismiss();

            }
        });
    }

    private void Initialize() {
        //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //다이얼로그의 배경을 투명으로 만듭니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //다이얼로그에서 사용할 레이아웃입니다.\
        setContentView(R.layout.matching_dialog);

        LottieAnimationView animationView = findViewById(R.id.profile_animation);
        animationView.playAnimation();
    }

    private void SetData(String hostName,
                         String guestName,
                         String userID,
                         int attendantNumber,
                         String contact,
                         String description) {

        ImageView imageView = findViewById(R.id.profile_img);
        Picasso.get().load(FacebookService.getInstance().getProfileURL(userID))
                .transform(CircleTransform.getInstance()).into(imageView);

        ((TextView) findViewById(R.id.intro_text)).
                setText(Html.fromHtml(
                        "<font color='#2186F8'>" + hostName + "</font>"
                                + " 님,<br />"
                                + "<font color='#2186F8'>" + guestName + "</font>"
                                + " 님과 함께 하시겠습니까?"));

        ((TextView) findViewById(R.id.attendant_number)).setText(attendantNumber + "명");
        ((TextView) findViewById(R.id.contact)).setText(contact);
        ((TextView) findViewById(R.id.reason)).setText(description);
    }

}

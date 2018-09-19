package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.activity.SplashActivity;
import com.depromeet.hanriver.hanrivermeetup.activity.main.MainActivity;
import com.facebook.login.LoginManager;

public class LogoutDialog extends Dialog{

    private Context mContext;
    private ImageButton positiveButton;
    private ImageButton negativeButton;

    public LogoutDialog(@NonNull Context context, MainActivity mainActivity) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.logout_dialog);     //다이얼로그에서 사용할 레이아웃입니다.

        mContext = context;
        positiveButton = findViewById(R.id.positive_button);
        negativeButton = findViewById(R.id.negative_button);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getContext(), SplashActivity.class);
                Toast.makeText(mContext, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                dismiss();
                mContext.startActivity(intent);
                mainActivity.finish();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}

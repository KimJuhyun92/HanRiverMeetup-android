package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;

public class MatchingDialog extends Dialog {
    public MatchingDialog(@NonNull Context context) {
        super(context);

        Initialize();


        //((TextView) findViewById(R.id.attendant_number)).setText("New Text");
    }

    private void Initialize() {
        //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //다이얼로그의 배경을 투명으로 만듭니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //다이얼로그에서 사용할 레이아웃입니다.\
        setContentView(R.layout.matching_dialog);
    }
}

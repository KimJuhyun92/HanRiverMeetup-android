package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;

public class SelectionDialog extends Dialog{
    ImageView positiveButton;
    ImageView negativeButton;

    public SelectionDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.selection_dialog);     //다이얼로그에서 사용할 레이아웃입니다.

        negativeButton = findViewById(R.id.negative_button);
        positiveButton = findViewById(R.id.positive_button);

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "exit", Toast.LENGTH_LONG).show();
                dismiss();   //다이얼로그를 닫는 메소드입니다.
            }
        });


    }
}

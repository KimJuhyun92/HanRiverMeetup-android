package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;

import org.w3c.dom.Text;

public class SelectionDialog extends Dialog{
    TextView copyButton;

    public SelectionDialog(@NonNull Context context, @NonNull String phoneNumber) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.selection_dialog);     //다이얼로그에서 사용할 레이아웃입니다.

        copyButton = findViewById(R.id.copy_button);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                context.startActivity(intent);
                dismiss();
            }
        });

//        negativeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "exit", Toast.LENGTH_LONG).show();
//                dismiss();   //다이얼로그를 닫는 메소드입니다.
//            }
//        });


    }
}

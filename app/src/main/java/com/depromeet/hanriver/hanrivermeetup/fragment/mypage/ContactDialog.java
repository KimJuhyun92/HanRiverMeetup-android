package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;

public class ContactDialog extends Dialog{
    private Context mContext;
    private TextView copyButton;
    private TextView contact_text;

    public ContactDialog(@NonNull Context context, @NonNull String contact) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.contact_dialog);     //다이얼로그에서 사용할 레이아웃입니다.

        mContext = context;
        copyButton = findViewById(R.id.copy_button);
        contact_text = findViewById(R.id.contact);

        if(isNumeric(contact) == true){
            contact_text.setText("연락처 : " + formCallNumber(contact));
        }
        else{
            contact_text.setText("카카오톡 : " + contact);
        }

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyContact(contact);
                Toast.makeText(mContext, "복사되었습니다.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public String formCallNumber(String contact){
        String callNumber = null;
        String num_first = contact.substring(0,3);
        String num_middle =  contact.substring(3,7);
        String num_last = contact.substring(7,11);
        callNumber = num_first + "-" + num_middle + "-" + num_last;

        return callNumber;
    }

    public void copyContact(String contact) {
        // 클립보드 객체 얻기
        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 클립데이터 생성
        ClipData clipData = ClipData.newPlainText("contact",contact);
        // 클립보드에 추가
        clipboardManager.setPrimaryClip(clipData);
    }

}

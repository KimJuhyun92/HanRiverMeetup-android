package com.depromeet.hanriver.hanrivermeetup.fragment.mypage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.Window;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;

public class MatchingDialog extends Dialog {
    public MatchingDialog(@NonNull Context context) {
        super(context);

        Initialize();

        SetData("김태성",
                "이미영",
                3,
                "010-6864-2758",
                "모임에 참여하게 된 이유를 적어주세요!모임에 참여하게 된 이유를 적어주세요!모임에 참여하게 된 이유를 적어주세요!!!");
    }

    private void Initialize() {
        //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //다이얼로그의 배경을 투명으로 만듭니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //다이얼로그에서 사용할 레이아웃입니다.\
        setContentView(R.layout.matching_dialog);
    }

    private void SetData(String hostName, String guestName, int attendantNumber, String contact, String reason) {
        ((TextView) findViewById(R.id.intro_text)).
                setText(Html.fromHtml(
                        "<font color='#2186F8'>" + hostName + "</font>"
                        + " 님,<br />"
                        + "<font color='#2186F8'>" + guestName + "</font>"
                        + " 님과 함께 하시겠습니까?"));

        ((TextView) findViewById(R.id.attendant_number)).setText(attendantNumber + "명");
        ((TextView) findViewById(R.id.contact)).setText(contact);
        ((TextView) findViewById(R.id.reason)).setText(reason);
    }
}

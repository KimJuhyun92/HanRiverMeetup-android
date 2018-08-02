package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;



@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    TextView textView;



    public TimePickerFragment(TextView textView) {
        this.textView = textView;

    }

    @Override
    public void onTimeSet(TimePicker timePicker,int hour,int minute) {
        String s_hour = String.valueOf(hour);
        String s_minute = String.valueOf(minute);
        if(s_minute.length()==1)
            s_minute = "0"+s_minute;
        textView.setText(""+s_hour+":"+s_minute);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

//        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),this,hour,min, DateFormat.is24HourFormat(getContext()));
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog,this,hour,min,DateFormat.is24HourFormat(getContext()));

        return timePickerDialog;
    }
}

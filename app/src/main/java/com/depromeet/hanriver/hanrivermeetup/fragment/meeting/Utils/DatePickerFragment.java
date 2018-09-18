package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextView textView;
    private Calendar minDate = Calendar.getInstance();
    private Calendar maxDate = Calendar.getInstance();

    public static DatePickerFragment newInstance(TextView textView) {

        Bundle args = new Bundle();

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        fragment.textView=textView;
        return fragment;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String s_year = String.valueOf(year);
        String s_month = String.valueOf(month+1);
        String s_day = String.valueOf(day);
        if(s_month.length()==1){
            s_month = "0"+s_month;
        }
        if(s_day.length()==1){
            s_day = "0"+s_day;
        }
        textView.setText(""+s_year+"-"+s_month+"-"+s_day);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog,this,hour,min,DateFormat.is24HourFormat(getContext()));
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),this,year,month,day);

        //최소 선택 가능 날짜 지정.
        minDate.set(year,month,day);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTime().getTime());

        return datePickerDialog;
    }
}

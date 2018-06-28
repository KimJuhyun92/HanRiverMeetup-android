package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;

import java.util.Arrays;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    LayoutInflater inflater;
    android.app.Activity act;
    List<Activity> list;


    public GridAdapter(android.app.Activity act, List<Activity> list) {
        this.act = act;
        this.list = list;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activitycategoryitem, parent, false);

        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.categoryitem_image);

        TextView textView = (TextView) convertView.findViewById(R.id.categoryitem_name);


//        imageView.setImageBitmap(list.get(i).getImage());
        Log.d("TAGTAGTAG",""+list.get(i).getName());
        textView.setText(""+list.get(i).getName());


 /*       imageView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
// TODO Auto-generated method stub
//이미지를 터치했을때 동작하는 곳

            }

        });*/


        return convertView;
    }
}

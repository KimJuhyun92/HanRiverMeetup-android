package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;

public class MeetingCategoryViewHolder extends ViewHolder{
    OnListItemClickListener mListener;
    public ImageView imgview;
    public TextView textview;

    public MeetingCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        imgview = itemView.findViewById(R.id.categoryitem_image);
        textview = itemView.findViewById(R.id.categoryitem_name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListItemClick(getAdapterPosition());
            }
        });
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener){
        mListener=onListItemClickListener;
    }

}

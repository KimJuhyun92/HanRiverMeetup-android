package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Category.OnListItemClickListener;

public class MeetingListViewHolder extends RecyclerView.ViewHolder{
    OnListItemClickListener mListener;
    public ImageView imgview;
    public TextView title;
    public TextView location;
    public TextView time;
    public TextView numofmem;
    public TextView fee;

    public MeetingListViewHolder(@NonNull View itemView) {
        super(itemView);
        imgview = itemView.findViewById(R.id.list_item_img);
        title = itemView.findViewById(R.id.list_item_title);
        location = itemView.findViewById(R.id.list_item_location);
        time = itemView.findViewById(R.id.list_item_time);
        numofmem = itemView.findViewById(R.id.list_item_numofmem);
        fee = itemView.findViewById(R.id.list_item_fee);
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

package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.CreateRoom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List.OnListitemClickListener;

public class LocationListViewHolder extends RecyclerView.ViewHolder {
    OnListitemClickListener mListener;
    TextView name;

    public LocationListViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.location_list_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListItemClick(getAdapterPosition());
            }
        });
    }

    public void setOnListItemClickListener(OnListitemClickListener onListItemClickListener){
        mListener=onListItemClickListener;
    }
}

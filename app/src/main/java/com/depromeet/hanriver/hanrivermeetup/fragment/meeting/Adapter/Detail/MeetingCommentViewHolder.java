package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter.Tab2Adapter;

public class MeetingCommentViewHolder extends RecyclerView.ViewHolder{

    public ImageView profile_img;
    public TextView nick_name;
    public TextView comment_time;
    public TextView comment_text;

    public MeetingCommentViewHolder(@NonNull View itemView) {
        super(itemView);
        profile_img = itemView.findViewById(R.id.comment_img);
        nick_name = itemView.findViewById(R.id.comment_name);
        comment_time = itemView.findViewById(R.id.comment_time);
        comment_text = itemView.findViewById(R.id.comment_content);
    }
}

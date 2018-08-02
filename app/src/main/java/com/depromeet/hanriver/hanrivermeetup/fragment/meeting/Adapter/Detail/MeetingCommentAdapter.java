package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCommentViewModel;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;

import java.util.List;

public class MeetingCommentAdapter extends RecyclerView.Adapter<MeetingCommentViewHolder>{

    List<Comment> list;
    Context context;
    Fragment fragment;

    public MeetingCommentAdapter(List<Comment> list, Context context, Fragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MeetingCommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_comment_item,viewGroup,false);
        MeetingCommentViewHolder holder  = new MeetingCommentViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingCommentViewHolder meetingCommentViewHolder, int i) {
        Comment comment = list.get(i);
        meetingCommentViewHolder.nick_name.setText(comment.getNickname());
        meetingCommentViewHolder.comment_text.setText(comment.getText());
        String time = comment.getCreatedTime();
        time = time.substring(11, 16);
        meetingCommentViewHolder.comment_time.setText(time);
        meetingCommentViewHolder.profile_img.setImageResource(R.drawable.app_logo);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

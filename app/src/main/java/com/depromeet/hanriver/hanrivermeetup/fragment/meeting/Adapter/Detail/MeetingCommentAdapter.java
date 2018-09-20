package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCommentViewModel;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Comment;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MeetingCommentAdapter extends BaseAdapter {
    List<Comment> list;
    Context context;
    Fragment fragment;


    public MeetingCommentAdapter(List<Comment> list, Context context, Fragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CommentViewHolder holder = new CommentViewHolder();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.detail_comment_item, viewGroup, false);

            holder.profile_img = view.findViewById(R.id.comment_img);
            holder.nick_name = view.findViewById(R.id.comment_name);
            holder.comment_time = view.findViewById(R.id.comment_time);
            holder.comment_text = view.findViewById(R.id.comment_content);
            view.setTag(holder);
        } else {
            holder = (CommentViewHolder) view.getTag();
        }

        Comment comment = list.get(i);
        holder.nick_name.setText(comment.getNickname());
        holder.comment_text.setText(comment.getText());
        String time = comment.getCreatedTime();
        time = time.substring(11, 16);
        holder.comment_time.setText(time);
        Picasso.get().load(FacebookService.getInstance().getProfileURL(list.get(i).getUserID()))
                .transform(CircleTransform.getInstance()).into(holder.profile_img);


        return view;
    }

    public class CommentViewHolder {
        public ImageView profile_img;
        public TextView nick_name;
        public TextView comment_time;
        public TextView comment_text;

    }
}

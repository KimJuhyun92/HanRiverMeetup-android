package com.depromeet.hanriver.hanrivermeetup.fragment.timeline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.TimeLineVO;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ItemViewHolder> {

    LayoutInflater inflater;
    private List<TimeLineVO> mItems;
    private android.app.Activity mAct;

    public TimeLineAdapter(android.app.Activity act) {
        mAct = act;
        mItems = new ArrayList<>();
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItems(List<TimeLineVO> items) {
        this.mItems.addAll(items);
    }

    public void clear() {
        mItems.clear();
//        mItems = new ArrayList<>();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileImageView;
        public ImageView imageView;
        public TextView nameTextView;
        public TextView locationTextView;
        public TextView contentTextView;

        public ItemViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.timeline_image);
            profileImageView = view.findViewById(R.id.timeline_profile_img);
            nameTextView = view.findViewById(R.id.timeline_profile_name);
            locationTextView = view.findViewById(R.id.timeline_profile_location);
            contentTextView = view.findViewById(R.id.timeline_text);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.timeline_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        TimeLineVO item = mItems.get(position);

        holder.nameTextView.setText(item.nickname);
        holder.locationTextView.setText(item.location);
        holder.contentTextView.setText(item.content);
        Picasso.get().load(item.imageurl).into(holder.imageView);
        Picasso.get().load(FacebookService.getInstance().getProfileURL(item.user_id))
                .transform(CircleTransform.getInstance()).into(holder.profileImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

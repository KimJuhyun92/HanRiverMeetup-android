package com.depromeet.hanriver.hanrivermeetup.fragment.timeline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.TimeLineVO;

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
        mItems = new ArrayList<>();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public ItemViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.image);
            mTextView = view.findViewById(R.id.textview);
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
        holder.mTextView.setText(item.getContent());
        //holder.mImageView.setImageResource(mItems.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

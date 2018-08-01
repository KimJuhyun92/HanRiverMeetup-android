package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.CreateRoom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List.MeetingListViewHolder;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List.OnListitemClickListener;

import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListViewHolder> implements OnListitemClickListener {

    List<String> list;
    Context context;
    DialogFragment fragment;
    TextView textView;

    public LocationListAdapter(List<String> list, Context context, DialogFragment fragment, TextView textView) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
        this.textView = textView;
    }

    @NonNull
    @Override
    public LocationListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_list_item, viewGroup, false);
        LocationListViewHolder holder = new LocationListViewHolder(v);
        holder.setOnListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationListViewHolder locationListViewHolder, int i) {

        locationListViewHolder.name.setText(list.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onListItemClick(int positon) {
        textView.setText(list.get(positon).toString());
        fragment.dismiss();
    }
}

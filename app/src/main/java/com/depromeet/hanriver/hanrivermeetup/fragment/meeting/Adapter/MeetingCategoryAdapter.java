package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingListFragment;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;

import java.util.List;

public class MeetingCategoryAdapter extends RecyclerView.Adapter<MeetingCategoryViewHolder> implements OnListItemClickListener {

    List<Activity> list;
    Context context;
    Fragment fragment;

    public MeetingCategoryAdapter(List<Activity> list,Context context,Fragment frag){
        this.list = list;
        this.context=context;
        fragment = frag;
    }

    @NonNull
    @Override
    public MeetingCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activitycategoryitem,viewGroup,false);
        MeetingCategoryViewHolder holder = new MeetingCategoryViewHolder(v);
        holder.setOnListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingCategoryViewHolder meetingCategoryViewHolder, int i) {
        Activity act = list.get(i);
        meetingCategoryViewHolder.textview.setText(act.getName());
        //meetingCategoryViewHolder.imgview.setImageResource(// 해당 이미지 아이디 넣어줄 것);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onListItemClick(int position) {
        Toast.makeText(context,""+list.get(position).getName()+" 눌림!",Toast.LENGTH_SHORT).show();
        FragmentTransaction fragTransaction = fragment.getFragmentManager().beginTransaction();
//        TestFrag frag = new TestFrag();
        MeetingListFragment frag = MeetingListFragment.newInstance();
        fragTransaction.replace(R.id.meeting_root, frag);
        fragTransaction.addToBackStack(null);
        fragTransaction.commit();
    }
}

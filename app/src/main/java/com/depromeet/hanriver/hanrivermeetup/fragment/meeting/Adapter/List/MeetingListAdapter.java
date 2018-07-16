package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Category.MeetingCategoryViewHolder;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.Category.OnListItemClickListener;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingListFragment;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Activity;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.Room;

import java.util.List;

public class MeetingListAdapter extends RecyclerView.Adapter<MeetingListViewHolder> implements OnListItemClickListener {
    List<Room> list;
    Context context;
    Fragment fragment;

    public MeetingListAdapter(List<Room> list,Context context,Fragment frag){
        this.list = list;
        this.context=context;
        fragment = frag;
    }

    @NonNull
    @Override
    public MeetingListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meeting_list_item,viewGroup,false);
        MeetingListViewHolder holder = new MeetingListViewHolder(v);
        holder.setOnListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingListViewHolder meetingListViewHolder, int i) {
        Room room = list.get(i);
        meetingListViewHolder.title.setText(room.getRoomName());
        meetingListViewHolder.imgview.setImageResource(room.getProfileImage());
        meetingListViewHolder.location.setText(room.getLocation());
        meetingListViewHolder.time.setText(room.getTime());
        meetingListViewHolder.numofmem.setText(""+room.getNumOfmember()+"명");
        meetingListViewHolder.fee.setText(""+room.getFee()+"원");
        //meetingCategoryViewHolder.imgview.setImageResource(// 해당 이미지 아이디 넣어줄 것);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onListItemClick(int position) {
//        FragmentTransaction fragTransaction = fragment.getFragmentManager().beginTransaction();
////        TestFrag frag = new TestFrag();
//        MeetingListFragment frag = MeetingListFragment.newInstance();
//        fragTransaction.replace(R.id.meeting_root, frag);
//        fragTransaction.addToBackStack(null);
//        fragTransaction.commit();
    }
}

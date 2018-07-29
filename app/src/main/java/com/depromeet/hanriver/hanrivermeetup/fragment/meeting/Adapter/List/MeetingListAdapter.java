package com.depromeet.hanriver.hanrivermeetup.fragment.meeting.Adapter.List;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCategoryFragment;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingCreateRoom;
import com.depromeet.hanriver.hanrivermeetup.fragment.meeting.MeetingDetailFragment;
import com.depromeet.hanriver.hanrivermeetup.model.meeting.MeetingDetail;

import java.util.List;

public class MeetingListAdapter extends RecyclerView.Adapter<MeetingListViewHolder> implements OnListitemClickListener {
    List<MeetingDetail> list;
    Context context;
    Fragment fragment;

    public MeetingListAdapter(List<MeetingDetail> list,Context context,Fragment frag){
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
        MeetingDetail room = list.get(i);
        meetingListViewHolder.title.setText(room.getTitle());
//        meetingListViewHolder.imgview.setImageResource();
        meetingListViewHolder.location.setText(room.getMeeting_location());
        String time = room.getMeeting_time();
        time = time.substring(11,16);
        Log.d("@@@@@","!@#!@#"+time);
        meetingListViewHolder.time.setText(time);
        meetingListViewHolder.numofmem.setText(""+room.getParticipants_cnt()+"명");
        meetingListViewHolder.fee.setText(""+room.getExpected_cost()+"원");
        //meetingCategoryViewHolder.imgview.setImageResource(// 해당 이미지 아이디 넣어줄 것);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onListItemClick(int position) {
//        FragmentTransaction fragmentTransaction = fragment.getFragmentManager().beginTransaction();
//        MeetingDetailFragment frag = MeetingDetailFragment.newInstance();
//        fragmentTransaction.replace(R.id.meeting_root,frag);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
        MeetingDetailFragment dialog = MeetingDetailFragment.newInstance();
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light);
        dialog.show(fragment.getFragmentManager(),"meeting_detail");
    }
}

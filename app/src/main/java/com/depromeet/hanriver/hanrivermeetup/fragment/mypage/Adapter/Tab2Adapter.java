package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab2VO;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tab2Adapter extends BaseAdapter{
    private LayoutInflater inflater;
    ArrayList<Tab2VO> mItems;
    private Context mContext;


    public Tab2Adapter (ArrayList<Tab2VO> items) {
        super();
        mItems = items;
    }



    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mypage_tab2_item, viewGroup, false);

            holder.mTitle = (TextView) view.findViewById(R.id.title);
            holder.mLocation = (TextView)view.findViewById(R.id.location);
            holder.mTime = (TextView)view.findViewById(R.id.meeting_time);
            holder.mCost = (TextView)view.findViewById(R.id.expected_cost);
            holder.mParticipants=(TextView)view.findViewById(R.id.participants_cnt);
            holder.imageView = (ImageView)view.findViewById(R.id.state);


            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        holder.mTitle.setText(mItems.get(i).getTitle());
        holder.mLocation.setText(mItems.get(i).getLocation());
        holder.mTime.setText(mItems.get(i).getMeeting_time());
        holder.mCost.setText(String.valueOf(mItems.get(i).getExpected_cost()));
        holder.mParticipants.setText(String.valueOf(mItems.get(i).getParticipants_cnt()));


        return view;
    }

    public void addItem(String mIcon, String mStr) {
//        Tab2VO item = new Tab2VO();
//
//        item.set(mIcon);
//        item.setStr(mStr);

//        mItems.add(item);
    }


    public class ViewHolder {
        private TextView mTitle;
        private TextView mLocation;
        private TextView mTime;
        private TextView mCost;
        private TextView mParticipants;
        private ImageView imageView;
    }

}

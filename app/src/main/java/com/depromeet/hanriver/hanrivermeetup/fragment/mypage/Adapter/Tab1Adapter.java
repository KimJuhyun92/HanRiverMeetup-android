package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.Tab1VO;

import java.util.List;

public class Tab1Adapter extends RecyclerView.Adapter<Tab1Adapter.ItemViewHolder>{
    LayoutInflater inflater;
    private List<Tab1VO> mItems;
    private android.app.Activity mAct;

    public Tab1Adapter(android.app.Activity act, List<Tab1VO> items) {
        mAct = act;
        mItems = items;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 커스텀 뷰홀더 
// item layout 에 존재하는 위젯들을 바인딩합니다. 
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public ItemViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.image);
            mTextView = view.findViewById(R.id.textview);
        }
    }

    // 새로운 뷰 홀더 생성 
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tab_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mTextView.setText(mItems.get(position).getText());
        holder.mImageView.setImageResource(mItems.get(position).getImg());
    }

    // 데이터 셋의 크기를 리턴해줍니다. 
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

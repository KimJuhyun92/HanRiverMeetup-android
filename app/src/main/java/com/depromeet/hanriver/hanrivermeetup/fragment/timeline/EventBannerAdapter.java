package com.depromeet.hanriver.hanrivermeetup.fragment.timeline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.EventVO;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventBannerAdapter extends PagerAdapter {
    @BindView(R.id.image) ImageButton imageButton;

    private Activity mActivity;
    private List<EventVO> mEventList;
    private LayoutInflater inflater;
    private int currentIndex = 0;

    public EventBannerAdapter(Activity activity, List<EventVO> eventList) {
        mActivity = activity;
        mEventList = eventList;
    }

    public void setCurrentIndex(int index) {
        currentIndex = index;
    }

    @Override
    public int getCount() {
        return mEventList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_evnet_image, container, false);
        ButterKnife.bind(this, viewLayout);

        imageButton.setOnClickListener(v -> {
            String url = mEventList.get(position).url;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            mActivity.startActivity(i);
        });

        Picasso.get().load(mEventList.get(position).imageurl).into(imageButton);
        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}

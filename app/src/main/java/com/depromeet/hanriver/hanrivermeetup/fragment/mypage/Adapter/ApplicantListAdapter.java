package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.MatchingDialog;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.SelectionDialog;
import com.depromeet.hanriver.hanrivermeetup.helper.CircleTransform;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.ApplicantVO;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ApplicantListAdapter extends RecyclerView.Adapter<ApplicantListAdapter.ItemViewHolder>{

    private List<ApplicantVO> itemsList;
    private Context mContext;
    private MatchingDialog dialog;
    private String userID;

    public ApplicantListAdapter(Context context, List<ApplicantVO> itemsList){
        this.itemsList = itemsList;
        this.mContext = context;
    }



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mypage_tab1_applicant_list_item, null);
        ItemViewHolder view = new ItemViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int i) {

        holder.applicant_name.setText(itemsList.get(i).getNickname());
        userID = itemsList.get(i).getUserId();

        Picasso.get().load(FacebookService.getInstance().getProfileURL(userID))
                .transform(CircleTransform.getInstance()).into(holder.applicant_img);

        holder.applicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new MatchingDialog(view.getContext(),itemsList.get(i));
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        protected ImageView applicant_img;
        protected TextView applicant_name;
        protected FrameLayout applicant;


        public ItemViewHolder(View view) {
            super(view);

            applicant_img = view.findViewById(R.id.applicant_img);
            applicant_name = view.findViewById(R.id.applicant_name);
            applicant = view.findViewById(R.id.applicant);

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Toast.makeText(v.getContext(), applicant_name.getText(), Toast.LENGTH_SHORT).show();
//
//                    //필요정보 : 아이템 포지션
//
//                    dialog = new MatchingDialog(v.getContext(),itemsList.get(position));
//                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                    dialog.show();
//                }
//            });
        }
    }
}

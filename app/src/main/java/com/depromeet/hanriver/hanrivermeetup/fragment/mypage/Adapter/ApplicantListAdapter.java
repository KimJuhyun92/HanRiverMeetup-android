package com.depromeet.hanriver.hanrivermeetup.fragment.mypage.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.hanriver.hanrivermeetup.R;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.MatchingDialog;
import com.depromeet.hanriver.hanrivermeetup.fragment.mypage.SelectionDialog;
import com.depromeet.hanriver.hanrivermeetup.model.mypage.ApplicantVO;
import com.depromeet.hanriver.hanrivermeetup.service.FacebookService;

import java.util.ArrayList;
import java.util.List;

public class ApplicantListAdapter extends RecyclerView.Adapter<ApplicantListAdapter.ItemViewHolder>{

    private List<ApplicantVO> itemsList;
    private Context mContext;
    private MatchingDialog dialog;

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

        String user_id = itemsList.get(i).getUserId();

        //참가자 이미지 비동기 통신
        new AsyncTask<Void,Void,Bitmap>(){

            @Override
            protected Bitmap doInBackground(Void... voids) {

                Bitmap bitmap ;
                bitmap = FacebookService.getInstance().getProfileURI(user_id);

                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                holder.applicant_img.setImageBitmap(bitmap);
            }
        }.execute();

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        protected ImageView applicant_img;
        protected TextView applicant_name;


        public ItemViewHolder(View view) {
            super(view);

            this.applicant_img = view.findViewById(R.id.applicant_img);
            this.applicant_name = view.findViewById(R.id.applicant_name);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), applicant_name.getText(), Toast.LENGTH_SHORT).show();

//                    dialog = new MatchingDialog(v.getContext());
//                    dialog.show();
//                    DisplayMetrics dm = v.getContext().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
//                    int width = dm.widthPixels; //디바이스 화면 너비
//                    int height = dm.heightPixels; //디바이스 화면 높이

                    dialog = new MatchingDialog(v.getContext());
//                    WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
//                    wm.copyFrom(dialog.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
//                    wm.width = width / 2;  //화면 너비의 절반
//                    wm.height = height / 2;  //화면 높이의 절반

                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    dialog.show();
                }
            });
        }
    }
}

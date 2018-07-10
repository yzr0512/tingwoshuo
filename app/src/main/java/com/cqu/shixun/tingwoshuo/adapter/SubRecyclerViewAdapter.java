package com.cqu.shixun.tingwoshuo.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.cqu.shixun.tingwoshuo.Constant;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.ContentItem;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by engineer on 2016/9/13.
 */
public class SubRecyclerViewAdapter extends RecyclerView.Adapter<SubRecyclerViewAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;
    Boolean isrecord = false;
    private List<ContentItem> datas = new ArrayList<>();
    private Context mContext;

    private int menuW, menuH;

    public SubRecyclerViewAdapter(Context mContext, List<ContentItem> datas) {
        this.datas = datas;
        this.mContext = mContext;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
        menuW = display.widthPixels / 2;
        menuH = LinearLayout.LayoutParams.WRAP_CONTENT;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headView);
        }


        View view = LayoutInflater.from(mContext).inflate(R.layout.sub_list_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }

        final int pos = getRealPosition(holder);


//        holder.text.setText(datas.get(pos));
        holder.StrQuesition.setText(datas.get(pos).getStrQuesition());
        holder.StrType.setText("推荐话题："+datas.get(pos).getStrType());
        holder.IntListenNum.setText("听过："+datas.get(pos).getIntListenNum());
        holder.StrName.setText("  "+datas.get(pos).getStrName());
        holder.BtnAnswer.setText(datas.get(pos).getIntListenPrice()+"听币偷偷听");

        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.StrAvatar);

    }

    @Override
    public int getItemViewType(int position) {
        if (headView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return headView == null ? datas.size() : datas.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CircleImageView StrAvatar;
        TextView StrName;
        TextView StrType;
        TextView StrQuesition;
        TextView IntListenNum;
        TextView IntListenPrice;
        MediaPlayer mediaPlayer;
        Button BtnAnswer;
        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;
            StrName = (TextView) itemView.findViewById(R.id.StrName);
            StrType = (TextView) itemView.findViewById(R.id.StrType);
            StrQuesition = (TextView) itemView.findViewById(R.id.StrQuesition);
            IntListenNum = (TextView) itemView.findViewById(R.id.IntListenNum);
            BtnAnswer = (Button) itemView.findViewById(R.id.BtnAnswer);
            StrAvatar=(CircleImageView)itemView.findViewById(R.id.StrAvatar);

            itemView.setOnClickListener(this);
            BtnAnswer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.BtnAnswer){
                int i = getLayoutPosition();
                Log.d("debug", Integer.toString(i));
                test(i);
//                if(isrecord==true){
//                    mediaPlayer=new MediaPlayer();
//                    try {
//
////                        mediaPlayer.setDataSource(datas.get(getAdapterPosition()).getFilePath());
//                        mediaPlayer.setDataSource("111");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        mediaPlayer.prepare();
//                        mediaPlayer.setLooping(false);
//                        mediaPlayer.start();
//                        isrecord=false;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }

            }else {
                Toast.makeText(mContext,"item"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void test(int i){
        Log.d("debug1", Integer.toString(i));

    }

    public void setHeadView(View view) {
        headView = view;
        notifyItemInserted(0);
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int pos = holder.getLayoutPosition();
        return headView == null ? pos : pos - 1;
    }
}

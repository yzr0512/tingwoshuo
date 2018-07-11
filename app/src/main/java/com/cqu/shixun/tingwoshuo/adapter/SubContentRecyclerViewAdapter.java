package com.cqu.shixun.tingwoshuo.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
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

import com.bumptech.glide.Glide;
import com.cqu.shixun.tingwoshuo.Constant;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.ContentItem;
import com.cqu.shixun.tingwoshuo.model.ListenContentItem;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.ListenRecordView.IListenRecordAdapterView;
import com.cqu.shixun.tingwoshuo.ui.ListenRecordView.IListenRecordAdapterPresenter;
import com.cqu.shixun.tingwoshuo.ui.ListenRecordView.ListenRecordAdapterPresenterImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by engineer on 2016/9/13.
 */
public class SubContentRecyclerViewAdapter extends RecyclerView.Adapter<SubContentRecyclerViewAdapter.MyViewHolder> implements IListenRecordAdapterView{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;
    private  int playstate=0;
    private int currItemPos = -1;
    private MediaPlayer mediaPlayer;
    User currUser;
    private List<ListenContentItem> datas = new ArrayList<>();
    private Context mContext;
    IListenRecordAdapterPresenter iListenRecordAdapterPresenter;

    public SubContentRecyclerViewAdapter(Context mContext, List<ListenContentItem> datas,User currUser) {
        this.datas = datas;
        this.mContext = mContext;
        this.currUser=currUser;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
        iListenRecordAdapterPresenter=new ListenRecordAdapterPresenterImpl(this,mContext);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headView);
        }


        View view = LayoutInflater.from(mContext).inflate(R.layout.sub_listcontent_item, null);
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
        holder.StrAskContent.setText(datas.get(pos).getStrAskContent());
        holder.IntListenContentNum.setText("听过："+datas.get(pos).getIntListenContentNum());
        holder.StrDate.setText(datas.get(pos).getStrDate());

        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.StrAskAvatar);
        Glide.with(mContext).load(Constant.headPics.get(pos % 2)).placeholder(R.drawable.profile).into(holder.StrListenAvatar);

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

    @Override
    public void play(Answer answer) {

        try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(answer.getAnswerPath());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
            playstate=1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void pause(){
        if(playstate==1){
            mediaPlayer.pause();
            playstate=2;
        }
        else if(playstate==2){
            mediaPlayer.start();
            playstate=1;
        }
    }
    @Override
    public void showMessage(String msg) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CircleImageView StrListenAvatar;
        CircleImageView StrAskAvatar;
        TextView StrAskContent;
        TextView IntListenContentNum;
        TextView StrDate;
        Button BtnListenContent;
        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;

            StrAskContent = (TextView) itemView.findViewById(R.id.StrAskContent);
            IntListenContentNum = (TextView) itemView.findViewById(R.id.IntListenContentNum);
            StrDate = (TextView) itemView.findViewById(R.id.StrDate);
            BtnListenContent = (Button) itemView.findViewById(R.id.BtnListenContent);
            StrListenAvatar=(CircleImageView)itemView.findViewById(R.id.StrListenAvatar);
            StrAskAvatar=(CircleImageView)itemView.findViewById(R.id.StrAskAvatar);
            itemView.setOnClickListener(this);
            BtnListenContent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.BtnListenContent){
                Toast.makeText(mContext,"111",Toast.LENGTH_SHORT).show();
                if(getAdapterPosition() == currItemPos){

                    pause();
                    if(playstate==1){
                        BtnListenContent.setText("播放中");
                    }
                    if(playstate==2){
                        BtnListenContent.setText("暂停中");
                    }
                    Log.d("isplay:",String.valueOf(mediaPlayer.isPlaying()));
                }else{
                    currItemPos = getAdapterPosition();
                    if(playstate == 1 || playstate == 2){
                        playstate = 0;
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                    iListenRecordAdapterPresenter.getQuestionAnswer(new Question(datas.get(getAdapterPosition()).getId()), currUser);
                    BtnListenContent.setText("播放中");
                }
            }
        }
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

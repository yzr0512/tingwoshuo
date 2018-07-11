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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cqu.shixun.tingwoshuo.Constant;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.AskContentItem;
import com.cqu.shixun.tingwoshuo.model.ListenContentItem;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.AskRecordView.AskRecordAdapterPresenterImpl;
import com.cqu.shixun.tingwoshuo.ui.AskRecordView.AskRecordPresenterImpl;
import com.cqu.shixun.tingwoshuo.ui.AskRecordView.IAskRecordAdapterPresenter;
import com.cqu.shixun.tingwoshuo.ui.AskRecordView.IAskRecordAdapterView;
import com.cqu.shixun.tingwoshuo.ui.AskRecordView.IAskRecordPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by engineer on 2016/9/13.
 */
public class SubAskContentRecyclerViewAdapter extends RecyclerView.Adapter<SubAskContentRecyclerViewAdapter.MyViewHolder> implements IAskRecordAdapterView{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;
    User currUser;
    private int currItemPos = -1;
    private MediaPlayer mediaPlayer;
    private  int playstate=0;
    IAskRecordAdapterPresenter iAskRecordAdapterPresenter;
    private List<AskContentItem> datas;
    private Context mContext;


    public SubAskContentRecyclerViewAdapter(Context mContext, List<AskContentItem> datas,User currUser) {
        this.datas = datas;
        this.mContext = mContext;
        this.currUser=currUser;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
        iAskRecordAdapterPresenter=new AskRecordAdapterPresenterImpl(this, mContext);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headView);
        }


        View view = LayoutInflater.from(mContext).inflate(R.layout.sub_asklistcontent_item, null);
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
        holder.IntQuesitionPrice.setText(datas.get(pos).getIntQuesitionPrice()+"听币");
        holder.StrDate.setText(datas.get(pos).getStrDate());
        holder.StrAskPerson.setText("  "+datas.get(pos).getStrAskPerson());
        holder.StrQuesitionState.setText(datas.get(pos).getStrQuesitionState());
        if(datas.get(pos).getStrQuesitionState().equals("已回答"))
        {
            holder.player.setVisibility(View.VISIBLE);//是否显示播放
        }
        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.StrAskAvatar);

    }

    @Override
    public int getItemViewType(int position) {
        if (headView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {

        //return headView == null ? datas.size() : datas.size() + 1;
        return datas.size();
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        CircleImageView StrAskAvatar;
        TextView StrAskContent;
        TextView StrDate;
        TextView StrQuesitionState;
        TextView StrAskPerson;
        TextView IntQuesitionPrice;
        Button player;//按钮实现

        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;

            StrAskPerson = (TextView) itemView.findViewById(R.id.sub_ask_StrAskPerson);
            StrAskContent = (TextView) itemView.findViewById(R.id.sub_ask_StrAskContent);
            StrDate = (TextView) itemView.findViewById(R.id.sub_ask_StrDate);
            StrQuesitionState = (TextView) itemView.findViewById(R.id.sub_ask_StrQuesitionState);
            IntQuesitionPrice = (TextView) itemView.findViewById(R.id.sub_ask_IntQuesitionPrice);
            StrAskAvatar=(CircleImageView)itemView.findViewById(R.id.sub_ask_StrAskAvatar);
            player=(Button)itemView.findViewById(R.id.askplayer);

            itemView.setOnClickListener(this);
            player.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.askplayer){
                Toast.makeText(mContext,"button"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
                if(getAdapterPosition() == currItemPos){
                    pause();
                    if(playstate==1){
                        player.setText("播放中");
                    }
                    if(playstate==2){
                        player.setText("暂停中");
                    }
                    Log.d("isplay:",String.valueOf(mediaPlayer.isPlaying()));
                }else{
                    currItemPos = getAdapterPosition();
                    if(playstate == 1 || playstate == 2){
                        playstate = 0;
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                    iAskRecordAdapterPresenter.getQuestionAnswer(new  Question(datas.get(getAdapterPosition()).getId()), currUser);
                    player.setText("播放中");
                }

            }else {
                Toast.makeText(mContext,"item"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
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

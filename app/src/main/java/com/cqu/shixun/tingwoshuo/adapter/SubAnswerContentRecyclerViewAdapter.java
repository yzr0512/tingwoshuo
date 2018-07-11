package com.cqu.shixun.tingwoshuo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.cqu.shixun.tingwoshuo.model.AnswerContentItem;
import com.cqu.shixun.tingwoshuo.model.AskContentItem;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.AnswerRecordView.AnswerRecordAdapterPresneterImpl;
import com.cqu.shixun.tingwoshuo.ui.AnswerRecordView.IAnswerRecordAdapterPresenter;
import com.cqu.shixun.tingwoshuo.ui.AnswerRecordView.IAnswerRecordAdapterView;
import com.cqu.shixun.tingwoshuo.ui.AnswerView.AnswerActivity;
import com.cqu.shixun.tingwoshuo.ui.AskView.WriteQuestionActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by engineer on 2016/9/13.
 */
public class SubAnswerContentRecyclerViewAdapter extends RecyclerView.Adapter<SubAnswerContentRecyclerViewAdapter.MyViewHolder>implements View.OnClickListener,IAnswerRecordAdapterView{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;
    private OnItemClickListener mItemClickListener;
    private List<AnswerContentItem> datas = new ArrayList<>();
    private Context mContext;
    IAnswerRecordAdapterPresenter iAnswerRecordAdapterPresenter;
    private  int playstate=0;
    private int currItemPos = -1;
    private MediaPlayer mediaPlayer;
    User currUser;

    public SubAnswerContentRecyclerViewAdapter(Context mContext, List<AnswerContentItem> datas,User currUser) {
        this.datas = datas;
        this.mContext = mContext;
        this.currUser=currUser;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
        iAnswerRecordAdapterPresenter=new AnswerRecordAdapterPresneterImpl(this,mContext);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headView);
        }


        View view = LayoutInflater.from(mContext).inflate(R.layout.sub_answerlistcontent_item, null);
        MyViewHolder holder = new MyViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v);
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        if(position>=0){

            holder.itemView.setTag(datas.get(position).getId());
        }

        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }

        final int pos = getRealPosition(holder);




//        holder.text.setText(datas.get(pos));
        holder.StrAskContent.setText(datas.get(pos).getStrAskContent());
        holder.IntQuesitionPrice.setText(datas.get(pos).getIntQuesitionPrice()+"听币");
        holder.StrDate.setText(datas.get(pos).getStrDate());
        holder.StrAskPerson.setText("  "+datas.get(pos).getStrAskPerson());
        holder.IntListenContentNum.setText(datas.get(pos).getIntListenContentNum()+"人听过");
//        holder.BtnListenContent.setOnClickListener(this);
//        holder.BtnReanswer.setOnClickListener(this);

        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.StrAskAvatar);
        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.StrAnswerAvatar);
        if(datas.get(pos).getStrIsAnswered().equals("待回答"))
        {
            holder.BtnListenContent.setVisibility(View.INVISIBLE);//是否显示播放
        }else{
            holder.BtnReanswer.setVisibility(View.INVISIBLE);
            holder.BtnListenContent.setVisibility(View.VISIBLE);//是否显示播放
        }


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
    public void onClick(View v) {

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

//    @Override
//    public void onClick(View v) {
//        if(v.getId()==R.id.sub_answer_BtnListenContent){
//
//        }
//        if(v.getId()==R.id.sub_answer_BtnReanswer){
//
//        }
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        CircleImageView StrAskAvatar;
        CircleImageView StrAnswerAvatar;
        Button BtnListenContent;
        Button BtnReanswer;
        TextView StrDate;
        TextView IntListenContentNum;
        TextView StrAskPerson;
        TextView IntQuesitionPrice;
        TextView StrAskContent;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            if (itemView == headView) return;

            IntListenContentNum = (TextView) itemView.findViewById(R.id.sub_answer_IntListenContentNum);
            StrAskContent = (TextView) itemView.findViewById(R.id.sub_answer_StrAskContent);
            StrDate = (TextView) itemView.findViewById(R.id.sub_answer_StrDate);
            StrAskPerson = (TextView) itemView.findViewById(R.id.sub_answer_StrAskPerson);
            IntQuesitionPrice = (TextView) itemView.findViewById(R.id.sub_answer_IntQuesiqionPrice);

            BtnListenContent=(Button)itemView.findViewById(R.id.sub_answer_BtnListenContent);
            BtnReanswer=(Button)itemView.findViewById(R.id.sub_answer_BtnReanswer);
            StrAskAvatar=(CircleImageView)itemView.findViewById(R.id.sub_answer_StrAskAvatar);
            StrAnswerAvatar=(CircleImageView)itemView.findViewById(R.id.sub_answer_StrAnswerAvatar);

            itemView.setOnClickListener(this);
            BtnReanswer.setOnClickListener(this);
            BtnListenContent.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

            if(v.getId()==R.id.sub_answer_BtnListenContent){
                Toast.makeText(mContext,"button"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
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
                    iAnswerRecordAdapterPresenter.getQuestionAnswer(new Question(datas.get(getAdapterPosition()).getId()), currUser);
                    BtnListenContent.setText("播放中");
                }

            }
            else if (v.getId()==R.id.sub_answer_BtnReanswer){
                Intent intent = new Intent();
                intent.setClass(mContext, AnswerActivity.class);
                intent.putExtra("questionID", datas.get(getAdapterPosition()).getId());
                mContext.startActivity(intent);

            }else {
                Toast.makeText(mContext,"item"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
            }

        }
//            if(v.getId()==R.id.sub_answer_BtnReanswer){
//                Toast.makeText(mContext,"buttonreanswer"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
//            }
//            if(v.getId()==R.id.sub_answer_BtnListenContent){
//                Toast.makeText(mContext,"buttonlisten"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
//            }
////            else {
////                Toast.makeText(mContext,"item"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
////            }
//        }
    }

    public void setHeadView(View view) {
        headView = view;
        notifyItemInserted(0);
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int pos = holder.getLayoutPosition();
        return headView == null ? pos : pos - 1;
    }

    public interface OnItemClickListener{
        void onItemClick(View position);
    }
    public void setmItemClickListener(SubAnswerContentRecyclerViewAdapter.OnItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }
}

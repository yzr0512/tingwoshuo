package com.cqu.shixun.tingwoshuo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cqu.shixun.tingwoshuo.Constant;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.AnswerContentItem;
import com.cqu.shixun.tingwoshuo.model.AskContentItem;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by engineer on 2016/9/13.
 */
public class SubAnswerContentRecyclerViewAdapter extends RecyclerView.Adapter<SubAnswerContentRecyclerViewAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;

    private List<AnswerContentItem> datas = new ArrayList<>();
    private Context mContext;


    public SubAnswerContentRecyclerViewAdapter(Context mContext, List<AnswerContentItem> datas) {
        this.datas = datas;
        this.mContext = mContext;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headView);
        }


        View view = LayoutInflater.from(mContext).inflate(R.layout.sub_answerlistcontent_item, null);
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
        holder.IntListenContentNum.setText(datas.get(pos).getIntListenContentNum()+"人听过");


        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.StrAskAvatar);
        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.StrAnswerAvatar);
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

    public class MyViewHolder extends RecyclerView.ViewHolder {


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

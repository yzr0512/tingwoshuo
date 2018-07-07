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
import com.cqu.shixun.tingwoshuo.model.AskContentItem;
import com.cqu.shixun.tingwoshuo.model.ListenContentItem;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by engineer on 2016/9/13.
 */
public class SubAskContentRecyclerViewAdapter extends RecyclerView.Adapter<SubAskContentRecyclerViewAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;

    private List<AskContentItem> datas = new ArrayList<>();
    private Context mContext;


    public SubAskContentRecyclerViewAdapter(Context mContext, List<AskContentItem> datas) {
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
        return headView == null ? datas.size() : datas.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        CircleImageView StrAskAvatar;
        TextView StrAskContent;
        TextView StrDate;
        TextView StrQuesitionState;
        TextView StrAskPerson;
        TextView IntQuesitionPrice;


        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;

            StrAskPerson = (TextView) itemView.findViewById(R.id.sub_ask_StrAskPerson);
            StrAskContent = (TextView) itemView.findViewById(R.id.sub_ask_StrAskContent);
            StrDate = (TextView) itemView.findViewById(R.id.sub_ask_StrDate);
            StrQuesitionState = (TextView) itemView.findViewById(R.id.sub_ask_StrQuesitionState);
            IntQuesitionPrice = (TextView) itemView.findViewById(R.id.sub_ask_IntQuesitionPrice);
            StrAskAvatar=(CircleImageView)itemView.findViewById(R.id.sub_ask_StrAskAvatar);

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

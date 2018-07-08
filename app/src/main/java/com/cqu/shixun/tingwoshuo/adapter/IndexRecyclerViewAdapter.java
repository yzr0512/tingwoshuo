package com.cqu.shixun.tingwoshuo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.cqu.shixun.tingwoshuo.Constant;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.PersonItem;
import com.cqu.shixun.tingwoshuo.util.ScreenUtil;
import com.cqu.shixun.tingwoshuo.widget.ListItemMenu;

/**
 * Created by engineer on 2016/9/13.
 */
public class IndexRecyclerViewAdapter extends RecyclerView.Adapter<IndexRecyclerViewAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;
    private OnItemClickListener mItemClickListener;
    private List<PersonItem> datas = new ArrayList<>();
    private Context mContext;

    private int menuW, menuH;

    public IndexRecyclerViewAdapter(Context mContext, List<PersonItem> datas) {
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

        View view = LayoutInflater.from(mContext).inflate(R.layout.index_list_item, null);
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



        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }
        final int pos = getRealPosition(holder);

        if(pos>=0){

            holder.itemView.setTag(datas.get(pos).getId());
        }

        if (pos == 1) {
            holder.liveList.setVisibility(View.VISIBLE);
            holder.normalShell.setVisibility(View.GONE);
        } else {
            holder.liveList.setVisibility(View.GONE);
            holder.normalShell.setVisibility(View.VISIBLE);
        }

        holder.ansNum.setText(datas.get(pos).getAnsNum()+"个回答 ");
        holder.askPrice.setText(datas.get(pos).getAskPrice()+"提问");
        holder.listenNum.setText(datas.get(pos).getListenNum()+"人收听 ");
        holder.title.setText(datas.get(pos).getTitle());
        holder.nametext.setText(datas.get(pos).getName());



        Glide.with(mContext).load(Constant.headPics.get(pos % 3)).placeholder(R.drawable.profile).into(holder.avatar);
       // Glide.with(mContext).load(Constant.itemPics.get(pos % 3)).placeholder(R.drawable.cardpic).into(holder.pic);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListItemMenu menu = new ListItemMenu(menuW, menuH, mContext);
                menu.update();
                int offx = ScreenUtil.dip2px(mContext, 24);
                int offy = ScreenUtil.dip2px(mContext, 24);
                menu.setAnimationStyle(R.style.MenuAnim);
                menu.showAsDropDown(holder.menu, -menuW + offx, -offy);

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.liveList.setLayoutManager(manager);
        final IndexLiveHListAdapter adapter = new IndexLiveHListAdapter(datas);
        holder.liveList.setAdapter(adapter);

//        holder.hidequesition.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setVisibility(View.GONE);
//                //adapter.notifyDataSetChanged();
//            }
//        });

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
        TextView ansNum;
        TextView listenNum;
        TextView nametext;//名字
        TextView title;
        Button askPrice;
        ImageView menu;//more
        CircleImageView avatar;//touxiang
       // ImageView pic;
        LinearLayout normalShell;
        RecyclerView liveList;
        TextView hidequesition;
        TextView share;

        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;
            menu = (ImageView) itemView.findViewById(R.id.menu);
            askPrice = (Button) itemView.findViewById(R.id.ask);//数据库获取？
            nametext = (TextView) itemView.findViewById(R.id.name_text);//名字
            title = (TextView) itemView.findViewById(R.id.title);
            ansNum = (TextView) itemView.findViewById(R.id.ansNum);
            listenNum = (TextView) itemView.findViewById(R.id.listenNum);
            avatar = (CircleImageView) itemView.findViewById(R.id.profile_image);//头像
          //  pic = (ImageView) itemView.findViewById(R.id.pic);
            normalShell = (LinearLayout) itemView.findViewById(R.id.normalList);
            liveList = (RecyclerView) itemView.findViewById(R.id.liveList);

            hidequesition=(TextView)itemView.findViewById(R.id.hidequestion);
            share=(TextView)itemView.findViewById(R.id.share);
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

    public interface OnItemClickListener{
        void onItemClick(View position);
    }
    public void setmItemClickListener(OnItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

}

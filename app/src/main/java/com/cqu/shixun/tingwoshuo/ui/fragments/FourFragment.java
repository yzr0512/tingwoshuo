package com.cqu.shixun.tingwoshuo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import android.widget.LinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;

import com.cqu.shixun.tingwoshuo.ui.activity.BecomeExpertActivity;
import com.cqu.shixun.tingwoshuo.ui.activity.MySettingActivity;
import com.cqu.shixun.tingwoshuo.ui.activity.RechargeActivity;
import com.cqu.shixun.tingwoshuo.R;

import java.util.List;
import android.content.Intent;
import static com.cqu.shixun.tingwoshuo.R.id.headview;


public class FourFragment extends Fragment {
    private View rootView;
    private Context mContext;
    private CollapsingToolbarLayout collapsing_toolbar;
    private FloatingActionButton fab;
    private LinearLayout L_my_money;
    private LinearLayout L_my_mantoman;
    private LinearLayout L_my_setting;
    //private  ViewPager vp;
    private static final String picUrl = "http://img1.imgtn.bdimg.com/it/u=3743691986,2983459167&fm=21&gp=0.jpg";
    List<String> mData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_four, container, false);
        //fra跳转到act，我的钱包
        L_my_money=(LinearLayout)rootView.findViewById(R.id.my_money);
        L_my_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), RechargeActivity.class);
                startActivity(intent);
            }
        });
        //进入开通答主
        L_my_mantoman=(LinearLayout)rootView.findViewById(R.id.my_mantoman);
        L_my_mantoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), BecomeExpertActivity.class);
                startActivity(intent);
            }
        });
        //进入我的设置
        L_my_setting=(LinearLayout)rootView.findViewById(R.id.my_setting);
        L_my_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), MySettingActivity.class);
                startActivity(intent);
            }
        });

        InitView();

        return rootView;
    }


    //接受跳转信息。
 /*   @Override
    public void onResume() {
    int id = getActivity().getIntent().getIntExtra("id", 0);
    if(id==2){
         vp.setCurrentItem(2);
    }
    super.onResume();
        }*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void InitView() {
        collapsing_toolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setTitle("个人中心");

        fab = (FloatingActionButton) rootView.findViewById(R.id.btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //姓名转换头像更改


                Toast.makeText(getContext(), "编辑", Toast.LENGTH_SHORT).show();
            }
        });
        CircleImageView view = (CircleImageView) rootView.findViewById(headview);
        Glide.with(mContext).load(picUrl).placeholder(R.drawable.profile).into(view);




    }



}

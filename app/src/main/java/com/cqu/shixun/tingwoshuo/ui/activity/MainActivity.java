package com.cqu.shixun.tingwoshuo.ui.activity;


import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.adapter.FragmentTabAdapter;
import com.cqu.shixun.tingwoshuo.fragments.ThirdSubAnswerFragment;
import com.cqu.shixun.tingwoshuo.ui.fragments.FourFragment;
import com.cqu.shixun.tingwoshuo.ui.fragments.ExpertListFragment;
import com.cqu.shixun.tingwoshuo.ui.fragments.SecondFragment;
import com.cqu.shixun.tingwoshuo.ui.fragments.ThirdFragment;

/**
 * Created by engineer on 2016/9/13.
 */
public class MainActivity extends FragmentActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    // private  ViewPager mViewPager;

    private FrameLayout content;
    private AppBarLayout index_app_bar;

    private List<Fragment> fragments = new ArrayList<>();

    //View
    private RadioGroup rgs;
    private RadioButton index_tab;
    private int currentIndex = 0;

//ac跳转到fra

 /* @Override
 protected void onResume() {
    int id = getIntent().getIntExtra("id", 0);
    if (id == 3) {
        Fragment fragmen3 = new FourFragment();
        FragmentManager fmanger = getSupportFragmentManager();
        FragmentTransaction transaction = fmanger.beginTransaction();
        transaction.replace(R.id.viewpager, fragmen3);
        transaction.commit();
        mViewPager.setCurrentItem(2);//
        //帮助跳转到指定子fragment
        Intent i=new Intent();
        i.setClass(MainActivity.this,FourFragment.class);
        i.putExtra("id",2);
    }
    super.onResume();
}
*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView() {
        content = (FrameLayout) findViewById(R.id.content);
        index_app_bar = (AppBarLayout) findViewById(R.id.index_app_bar);
        rgs = (RadioGroup) findViewById(R.id.tabs_rg);
        index_tab = (RadioButton) findViewById(R.id.home_tab);
        fragments.add(new ExpertListFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new FourFragment());

        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.content, rgs);

        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                super.OnRgsExtraCheckedChanged(radioGroup, checkedId, index);
                Log.e("CheckedChanged", "-----" + index);
                currentIndex = index;
                resetView();
                switch (index) {
                    case 0:
//                        index_app_bar.setVisibility(View.GONE); // 浮动顶部条
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;

                }
            }
        });


    }

    private void resetView() {
        index_app_bar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() { // 在MainActivity按返回键默认跳到第一页

        if (currentIndex != 0) {
            index_tab.setChecked(true);
        } else {
            super.onBackPressed();
        }
    }


    private android.support.v4.app.FragmentManager fmanager;
    private android.support.v4.app.FragmentTransaction ftransaction;

//    @SuppressLint("ResourceType")
//    public void gotoDownloadFragment() {    //去下载页面
//        fmanager = getSupportFragmentManager();
//        ftransaction = fmanager.beginTransaction();
//        ExpertListFragment mExpertListFragment = new ExpertListFragment();
//        final android.support.v4.app.FragmentTransaction replace = ftransaction.replace(R.layout.fragment_index, ExpertListFragment);
//        ftransaction.commit();
//
//    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1 && resultCode==2){
            fmanager=getSupportFragmentManager();
            ftransaction=fmanager.beginTransaction();
            ftransaction.replace(R.id.content,new ExpertListFragment());
            ftransaction.commit();
        }
        if(requestCode==3 && resultCode==4){
            fmanager=getSupportFragmentManager();
            ftransaction=fmanager.beginTransaction();
            ftransaction.replace(R.id.content,new ThirdSubAnswerFragment());
            ftransaction.commit();
        }
    }

}
package com.cqu.shixun.tingwoshuo.ui.ExpertListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.model.Category;
 import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.ExpertInfoView.ExpertInformationActivity;
import com.cqu.shixun.tingwoshuo.model.PersonItem;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.adapter.IndexRecyclerViewAdapter;

public class ExpertListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IExpertListView {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private IndexRecyclerViewAdapter adapter;
    public Button buProperty;
    public Button buFinancial;
    public Button buEmotion;
    public Button buLaw;
    private View rootView;
    private FloatingActionMenu fam;

    IExpertListPrsenter iExpertListPrsenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_index, null);
        InitView();

      //  View headView = LayoutInflater.from(mContext).inflate(R.layout.index_list_headview, null);

        iExpertListPrsenter = new ExpertListPresentImpl(this);
        iExpertListPrsenter.getExpertList("房产");



        return rootView;
    }

    private void InitView() {
        fam = (FloatingActionMenu) rootView.findViewById(R.id.menu_yellow);
        //头像实现处

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);



    }
//    public void  onItemClick(int position){
//        Toast.makeText(getActivity().getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }

    @Override
    public void showCategoryList(List<Category> categories) {
        // 前端完成此处的实现

    }



    @Override
    public void showExpertList(String categoryName, List<User> users) {
        // 前端完成此处的实现
        final List<PersonItem>datas=new ArrayList<>();
        for(User user : users){
            PersonItem personItem=new PersonItem(user.getId());
            personItem.setAnsNum(user.getAnsNum());
            personItem.setAskPrice(user.getAskPrice());
            personItem.setImg("hahah");
            personItem.setListenNum(500);
            personItem.setName(user.getName());
            personItem.setTitle(user.getTitle());
            datas.add(personItem);
        }


        View headView = LayoutInflater.from(mContext).inflate(R.layout.index_list_headview, null);
        adapter = new IndexRecyclerViewAdapter(mContext, datas);
        adapter.setHeadView(headView);   //设置4个范围下面的灰块区域
//        adapter.setmItemClickListener((IndexRecyclerViewAdapter.OnItemClickListener) this);

        buProperty=(Button)headView.findViewById(R.id.BtnProperty);
        buProperty.setOnClickListener(new View.OnClickListener() {
           // @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                buProperty.setBackgroundResource(R.drawable.house_65_v2);
                iExpertListPrsenter.getExpertList("房产");

            }
        });

        buLaw=(Button)headView.findViewById(R.id.BtnLaw);
        buLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buLaw.setBackgroundResource(R.drawable.law_65_v2);
                iExpertListPrsenter.getExpertList("法律");

            }
        });

        buFinancial=(Button)headView.findViewById(R.id.BtnFinancial);
        buFinancial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buFinancial.setBackgroundResource(R.drawable.cash_65_v2);
                iExpertListPrsenter.getExpertList("理财");

            }
        });

        buEmotion=(Button)headView.findViewById(R.id.BtnEmotion);
        buEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buEmotion.setBackgroundResource(R.drawable.love_65_v2);
                iExpertListPrsenter.getExpertList("情感");

            }
        });

        adapter.setmItemClickListener(new IndexRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View position) {
                Toast.makeText(getActivity().getApplicationContext(),"onItemClick:"+position.getTag().toString(),Toast.LENGTH_SHORT).show();
                Log.d("positon.getid():",position.getTag().toString());
                Intent intent=new Intent();

                intent.setClass(getActivity(), ExpertInformationActivity.class);
                getActivity().startActivityForResult(intent,1);
                intent.putExtra("expertID",Integer.valueOf(position.getTag().toString()));

                startActivity(intent);

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > 5) {
                    if (dy > 0) {
                        fam.hideMenu(true);
                    } else {
                        fam.showMenu(true);
                    }
                }
            }
        });

       // iExpertListPrsenter = new ExpertListPresentImpl(this);

       // iExpertListPrsenter.getCategoryList();//板块列表


    }

    @Override
    public void showMessage(String msg) {
        Toast t = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        t.show();
    }


}

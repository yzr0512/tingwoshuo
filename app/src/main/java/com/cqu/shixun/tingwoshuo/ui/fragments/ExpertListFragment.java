package com.cqu.shixun.tingwoshuo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.model.Category;
// import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IExpertListPrsenter;
import com.cqu.shixun.tingwoshuo.presenter.impl.ExpertListPresentImpl;
import com.cqu.shixun.tingwoshuo.ui.iView.IExpertListView;
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
    //
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
        return rootView;
    }

    private void InitView() {
        fam = (FloatingActionMenu) rootView.findViewById(R.id.menu_yellow);
        //头像实现处
        View headView = LayoutInflater.from(mContext).inflate(R.layout.index_list_headview, null);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        List<PersonItem>datas=new ArrayList<>();
        for(int i=0;i<5;i++){
            PersonItem personItem=new PersonItem(i);
            personItem.setAnsNum(30);
            personItem.setAskPrice(50+i);
            personItem.setImg("hahah");
            personItem.setListenNum(500+2*i);
            personItem.setName("余志荣");
            personItem.setTitle("DNF肝帝,广东粤语倡导者，资深软件架构师,电脑清洗专家。");
            datas.add(personItem);
        }
//        List<String> datas = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            datas.add("This is item " + i);
//        }
        adapter = new IndexRecyclerViewAdapter(mContext, datas);
        adapter.setHeadView(headView);
//        adapter.setmItemClickListener((IndexRecyclerViewAdapter.OnItemClickListener) this);

        adapter.setmItemClickListener(new IndexRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View position) {
                Toast.makeText(getActivity().getApplicationContext(),"onItemClick:"+position.getTag(),Toast.LENGTH_SHORT).show();
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

        iExpertListPrsenter = new ExpertListPresentImpl(this);

        iExpertListPrsenter.getCategoryList();//板块列表


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

        for( Category   category: categories) {

            category.getName();//分类名

            //显示出来到前端





        }



    }

    @Override
    public void showExpertList(Category category, List<PersonItem> personItems) {
        // 前端完成此处的实现



    }

}

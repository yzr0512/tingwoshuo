package com.cqu.shixun.tingwoshuo.ui.ListenRecordView;

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

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.adapter.SubContentRecyclerViewAdapter;
import com.cqu.shixun.tingwoshuo.adapter.SubRecyclerViewAdapter;
import com.cqu.shixun.tingwoshuo.model.ContentItem;
import com.cqu.shixun.tingwoshuo.model.ListenContentItem;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ThirdSubListenFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IListenRecordView {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SubContentRecyclerViewAdapter adapter;
    //
    private View rootView;
    private FloatingActionButton fab;

    IListenRecordPresenter iListenRecordPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_second_sub, null);
        InitView();
        iListenRecordPresenter = new ListenRecordPresenterImpl(this);
        return rootView;
    }

    private void InitView() {
        fab = (FloatingActionButton) rootView.findViewById(R.id.sub_fab);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);

        List<ListenContentItem>datas=new ArrayList<>();
        for (int i=0;i<100;i++){
            ListenContentItem listenContentItem=new ListenContentItem();
            listenContentItem.setStrDate("2018-07-05");
            listenContentItem.setIntListenContentNum(33+i);
            listenContentItem.setStrAskContent("重庆合川北新御龙湾的房产是否具有投资价值？升值空间多大？");
            datas.add(listenContentItem);
        }

        adapter = new SubContentRecyclerViewAdapter(mContext, datas);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > 5) {
                    if (dy > 0) {
                        fab.hide(true);
                    } else {
                        fab.show(true);
                    }
                }
            }
        });

    }


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
    public void showListenRecordList(List<Question> questions) {

    }

    @Override
    public void showMessage(String msg) {
        Toast t = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        t.show();
    }

}

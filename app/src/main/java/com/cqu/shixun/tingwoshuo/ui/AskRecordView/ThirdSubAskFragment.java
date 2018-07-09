package com.cqu.shixun.tingwoshuo.ui.AskRecordView;

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
import com.cqu.shixun.tingwoshuo.adapter.SubAskContentRecyclerViewAdapter;
import com.cqu.shixun.tingwoshuo.model.AskContentItem;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ThirdSubAskFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IAskRecordView {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SubAskContentRecyclerViewAdapter adapter;
    //
    private View rootView;
    private FloatingActionButton fab;

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

        List<AskContentItem>datas=new ArrayList<>();
        for (int i=0;i<100;i++){
            AskContentItem askContentItem=new AskContentItem();
            askContentItem.setStrDate("2018-07-05");
            askContentItem.setIntQuesitionPrice(13+i);
            askContentItem.setStrAskPerson("梁宏观");
            askContentItem.setStrQuesitionState("等待回答");
            askContentItem.setStrAskContent("重庆大学创校多长时间了？");
            datas.add(askContentItem);
        }

        adapter = new SubAskContentRecyclerViewAdapter(mContext, datas);
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
    public void showAskRecordList(List<Question> questions) {

    }

    @Override
    public void showMessage(String msg) {
        Toast t = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        t.show();
    }


}

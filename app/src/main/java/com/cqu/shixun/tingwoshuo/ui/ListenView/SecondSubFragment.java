package com.cqu.shixun.tingwoshuo.ui.ListenView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.MyApplication;
import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.ContentItem;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.AskView.WriteQuestionActivity;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.adapter.SubRecyclerViewAdapter;

public class SecondSubFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IListenView {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SubRecyclerViewAdapter adapter;
    //
    private View rootView;
    private FloatingActionButton fab;

    IListenPresenter iListenPresenter;

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
        iListenPresenter = new ListenPresenterImpl(this, this.getContext());
        iListenPresenter.getQuestionList("房产");
//        iListenPresenter.getQuestionAnswer(new Question(1), new User(0), this.getActivity().getExternalFilesDir("").getAbsolutePath());

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
//        List<ContentItem>datas=new ArrayList<>();
//        for (int i=0;i<100;i++){
//            ContentItem contentItem=new ContentItem();
//            contentItem.setIntListenNum(30+i);
//            contentItem.setStrType("精选");
//            contentItem.setStrAvatar("hahah");
//            contentItem.setIntListenPrice(500+2*i);
//            contentItem.setStrName("陶友玮");
//            contentItem.setStrQuesition("重庆合川北新御龙湾的房产是否具有投资价值？升值空间多大？");
//            datas.add(contentItem);
//        }
//
//        adapter = new SubRecyclerViewAdapter(mContext, datas);
//        recyclerView.setAdapter(adapter);
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (Math.abs(dy) > 5) {
//                    if (dy > 0) {
//                        fab.hide(true);
//                    } else {
//                        fab.show(true);
//                    }
//                }
//            }
//        });

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
    public void showQuestionList(List<Question> questions) {
        List<ContentItem> datas = new ArrayList<>();
        for(Question question : questions){
            ContentItem contentItem = new ContentItem();
            contentItem.setIntListenNum(question.getListenNum());
            contentItem.setStrType("精选");
            contentItem.setStrAvatar("hhh");
            contentItem.setQuesitionId(question.getId());
            contentItem.setIntListenPrice((int)question.getListenPrice());
            contentItem.setStrName(question.getQuestionerName());
            contentItem.setStrQuesition(question.getContent());
            datas.add(contentItem);
        }

        adapter = new SubRecyclerViewAdapter(mContext, datas, ((MyApplication)getActivity().getApplication()).getCurrUser());
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
    public void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}

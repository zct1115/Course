package com.example.zct11.course.ui.home.mainfragment;

import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.FoundAdapter;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.bean.GankIoDataBean;
import com.example.zct11.course.ui.found.presenter.FoundPresenter;
import com.example.zct11.course.ui.found.view.FoundView;
import com.example.zct11.course.widget.webview.WebViewActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */

public class MessageFragment extends Fragment implements FoundView ,FoundAdapter.onItemClickListenr{


    private FoundPresenter presenter;
    private FoundAdapter adapter;
    private List<GankIoDataBean.ResultsBean> data=new ArrayList<>();
    private XRecyclerView recyclerView;
    private int page=10;
    private int pre_page=1;
    private Toolbar toolbar;

    public static MessageFragment getInstance (){
        MessageFragment messageFragment=new MessageFragment();
        return  messageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, null);
        presenter=new FoundPresenter(this);
        presenter.getData("Android",page,pre_page);

        initView(v);

        return v;
    }

    private void initView(View v) {
        recyclerView= (XRecyclerView) v.findViewById(R.id.xrecycleview);
        toolbar=(Toolbar)v.findViewById(R.id.tool);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("发现每一个细节");
        adapter=new FoundAdapter(data, CourseApplication.getAppContext());
        adapter.setOnItemClickListener(this);
        LinearLayoutManager manager=new LinearLayoutManager(CourseApplication.getAppContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //recyclerView.setPullRefreshEnabled(false);
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                pre_page++;
                presenter.getData("Android",page,pre_page);
                recyclerView.loadMoreComplete();
            }
        });

    }

    @Override
    public void getData(List<GankIoDataBean.ResultsBean> mdata) {
       if(mdata!=null){
          if(pre_page==1){
              data.clear();
              data.addAll(mdata);
              adapter.notifyDataSetChanged();
          }else {
              if(mdata.size()<page){
                  page=mdata.size();
              }
              data.addAll(mdata);
              adapter.notifyDataSetChanged();
          }
       }
    }

    @Override
    public void onItemClick(int position) {
        WebViewActivity.loadUrl(CourseApplication.getAppContext(), data.get(position).getUrl(), "加载中...");
    }
}

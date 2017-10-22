package com.example.zct11.course.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.CourseAdapter;
import com.example.zct11.course.adapter.CourseHtmlAdapter;
import com.example.zct11.course.bean.HtmlData;
import com.example.zct11.course.bean.MovingData;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */

public class Htmling extends Fragment {

    private XRecyclerView xRecyclerView;
    private CourseHtmlAdapter adapter;
    private List<HtmlData> data=new ArrayList<>();

    public static Htmling getInstance (){
        Htmling htmling=new Htmling();
        return  htmling;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_html,null);
        initData();
        xRecyclerView= (XRecyclerView) v.findViewById(R.id.htmling);
        adapter=new CourseHtmlAdapter(data,getContext());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(manager);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.refreshComplete();
            }
        });
        xRecyclerView.setAdapter(adapter);
        return v;
    }

    private void initData() {
        HtmlData moving=new HtmlData();
        moving.setImg("http://img.mukewang.com/5909383700016b7906000338-240-135.jpg");
        moving.setNum(1481);
        moving.setPrice(250);
        moving.setType("android");
        moving.setTitle("JS实现京东无延迟菜单效果");
        moving.setStep("中级");
        HtmlData main1=new HtmlData();
        main1.setImg("http://img2.mukewang.com/590053190001556d06000338-240-135.jpg");
        main1.setNum(500);
        main1.setPrice(0);
        main1.setType("前端");
        main1.setTitle("bootstrap快速入门");
        main1.setStep("高级");
        HtmlData main2=new HtmlData();
        main2.setImg("http://img4.mukewang.com/59bfab740001369a06000338-240-135.jpg");
        main2.setNum(1121);
        main2.setPrice(300);
        main2.setType("android");
        main2.setTitle("阿里D2前端技术论坛——2016初心");
        main2.setStep("中级");

        data.add(moving);
        data.add(main1);
        data.add(main2);
    }
}

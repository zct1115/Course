package com.example.zct11.course.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.MainAdapter;
import com.example.zct11.course.adapter.SprogAdapter;
import com.example.zct11.course.bean.MainRecommend;
import com.example.zct11.course.utils.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.transformer.TabletTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */

public class Recommend extends Fragment {


    private Banner banner;
    private RecyclerView recycleview;
    private RecyclerView newrecycleview;
    private MainAdapter adapter;
    private SprogAdapter sprogadapter;
    private List<MainRecommend> data=new ArrayList<>();
    private List<MainRecommend> newdata=new ArrayList<>();
    private Context context;

    public static Recommend getInstance() {
        Recommend recommend = new Recommend();
        return recommend;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recommend, null);
        context=getActivity();
        initView(v);
        return v;
    }


    private void initView(View v) {
        banner= (Banner) v.findViewById(R.id.banner);
        List<String > imgs=new ArrayList<>();
        imgs.add("http://img.mukewang.com/59e87bd600014fd009360316.jpg");
        imgs.add("http://img.mukewang.com/59e8093900013bbf09360316.jpg");
        imgs.add("http://img.mukewang.com/59e87bbe0001cf7e09360316.jpg");
        imgs.add("http://img.mukewang.com/59e87d2c0001e6e009360316.jpg");
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imgs);
        banner.setBannerAnimation(Transformer.Tablet);
        banner.setDelayTime(4000);
        banner.startAutoPlay();
        banner.start();

        initData();

        recycleview= (RecyclerView) v.findViewById(R.id.xrecycleview);
        adapter=new MainAdapter(data,context);
        newrecycleview= (RecyclerView) v.findViewById(R.id.sprog);
        sprogadapter=new SprogAdapter(newdata,context);
        GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager1 = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(manager);
        newrecycleview.setLayoutManager(manager1);
        recycleview.setAdapter(adapter);
        newrecycleview.setAdapter(sprogadapter);

    }

    private void initData() {
        MainRecommend main=new MainRecommend();
        main.setImg("http://img1.sycdn.imooc.com/szimg/5909a1250001197e05400300-360-202.jpg");
        main.setNum(1521);
        main.setPrice(200);
        main.setType("android");
        main.setTitle("BAT大咖助力 全面升级Android面试");
        main.setStep("中级");
        MainRecommend main1=new MainRecommend();
        main1.setImg("http://img1.sycdn.imooc.com/szimg/5931273d0001289a05400300-360-202.jpg");
        main1.setNum(500);
        main1.setPrice(0);
        main1.setType("前端");
        main1.setTitle("全程真实数据对接 带你从0开发前后端分离的企业级上");
        main1.setStep("高级");
        MainRecommend main2=new MainRecommend();
        main2.setImg("http://img1.sycdn.imooc.com/szimg/595e01c50001d24805400300-360-202.jpg");
        main2.setNum(1121);
        main2.setPrice(300);
        main2.setType("后端");
        main2.setTitle("Spring Boot企业微信点餐系统");
        main2.setStep("中级");
        MainRecommend main3=new MainRecommend();
        main3.setImg("http://img1.sycdn.imooc.com/szimg/59b8a486000107fb05400300-360-202.jpg");
        main3.setNum(560);
        main3.setPrice(0);
        main3.setType("Python");
        main3.setTitle("全网最热的Python3入门+进阶 比自学更快上手实际");
        main3.setStep("低级");


        MainRecommend main4=new MainRecommend();
        main4.setImg("http://img1.sycdn.imooc.com/szimg/595067d5000132a105400300-360-202.jpg");
        main4.setNum(1021);
        main4.setPrice(100);
        main4.setType("JavaScript");
        main4.setTitle("前端JavaScript面试技巧");
        main4.setStep("低级");
        MainRecommend main5=new MainRecommend();
        main5.setImg("http://img1.mukewang.com/598186000001813106000338-240-135.jpg");
        main5.setNum(12500);
        main5.setPrice(0);
        main5.setType("Python");
        main5.setTitle("python自动化运维篇");
        main5.setStep("低级");
        MainRecommend main6=new MainRecommend();
        main6.setImg("http://img1.sycdn.imooc.com/szimg/5949d43a0001be6705400300-360-202.jpg");
        main6.setNum(1821);
        main6.setPrice(30);
        main6.setType("算法");
        main6.setTitle("Java c++ 算法与数据结构精讲");
        main6.setStep("低级");
        MainRecommend main7=new MainRecommend();
        main7.setImg("http://img4.mukewang.com/57035f4a0001598106000338-240-135.jpg");
        main7.setNum(1560);
        main7.setPrice(0);
        main7.setType("Go");
        main7.setTitle("Go语言第一课");
        main7.setStep("低级");
        data.add(main);
        data.add(main1);
        data.add(main2);
        data.add(main3);
        newdata.add(main4);
        newdata.add(main5);
        newdata.add(main6);
        newdata.add(main7);
    }
}

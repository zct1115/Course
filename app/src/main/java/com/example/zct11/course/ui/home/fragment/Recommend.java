package com.example.zct11.course.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;
import com.example.zct11.course.utils.GlideImageLoader;
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

    public static Recommend getInstance() {
        Recommend recommend = new Recommend();
        return recommend;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recommend, null);
        initData(v);
        return v;
    }

    private void initData(View v) {
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

    }
}

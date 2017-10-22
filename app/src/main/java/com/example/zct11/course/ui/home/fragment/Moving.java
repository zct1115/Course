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
import com.example.zct11.course.bean.MainRecommend;
import com.example.zct11.course.bean.MovingData;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */

public class Moving extends Fragment {

    private XRecyclerView xRecyclerView;
    private CourseAdapter adapter;
    private List<MovingData> data=new ArrayList<>();

    public static Moving getInstance (){
        Moving moving=new Moving();
        return  moving;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_moving,null);
        initData();
        xRecyclerView= (XRecyclerView) v.findViewById(R.id.moving);
        adapter=new CourseAdapter(data,getContext());
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
        MovingData moving=new MovingData();
        moving.setImg("http://img.mukewang.com/5833e62b0001c2ad06000338-240-135.jpg");
        moving.setNum(1521);
        moving.setPrice(200);
        moving.setType("android");
        moving.setTitle("Android框架-GreenDao");
        moving.setStep("中级");
        MovingData main1=new MovingData();
        main1.setImg("http://img1.mukewang.com/599150070001993506000338-240-135.jpg");
        main1.setNum(500);
        main1.setPrice(0);
        main1.setType("前端");
        main1.setTitle("Android图表绘制之直方图");
        main1.setStep("高级");
        MovingData main2=new MovingData();
        main2.setImg("http://img3.mukewang.com/590a8c9400013c5406000338-240-135.jpg");
        main2.setNum(1121);
        main2.setPrice(300);
        main2.setType("android");
        main2.setTitle("Android常用异常集及解决方案");
        main2.setStep("中级");

        data.add(moving);
        data.add(main1);
        data.add(main2);
    }
}

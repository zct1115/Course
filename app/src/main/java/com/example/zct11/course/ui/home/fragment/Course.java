package com.example.zct11.course.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.MypagerAdapter;
import com.example.zct11.course.utils.ViewFindUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/20.
 */

public class Course extends Fragment {


    private Context mContext;
    private SlidingTabLayout layout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "前端开发", "移动开发", "后端开发", "数据库", "云计算", "UI设计"
    };
    private MypagerAdapter mAdapter;

    public static Course getInstance() {
        Course course = new Course();
        return course;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course, null);
        mContext = getActivity();
        layout = ViewFindUtils.find(v, R.id.tl_5);
        final ViewPager vp = ViewFindUtils.find(v,R.id.vp);
        initView();
        mAdapter = new MypagerAdapter(getFragmentManager(), mFragments);
        vp.setAdapter(mAdapter);
        layout.setViewPager(vp, mTitles);
        layout.setCurrentTab(0);
        layout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(6);
        return v;
    }

    private void initView() {
        mFragments.add(Htmling.getInstance());
        mFragments.add(Moving.getInstance());
        mFragments.add(Webing.getInstance());
        mFragments.add(Database.getInstance());
        mFragments.add(Clouding.getInstance());
        mFragments.add(Cssing.getInstance());
    }
}

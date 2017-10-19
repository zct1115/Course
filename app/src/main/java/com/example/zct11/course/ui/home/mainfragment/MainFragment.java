package com.example.zct11.course.ui.home.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.TabEntity;
import com.example.zct11.course.ui.home.fragment.Course;
import com.example.zct11.course.ui.home.fragment.Recommend;
import com.example.zct11.course.utils.ViewFindUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/19.
 */

public class MainFragment extends Fragment {

    private SegmentTabLayout mTabLayout;
    private View view;
    private ViewPager viewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();





    public static MainFragment getInstance (){
        MainFragment mainFragment=new MainFragment();
        return  mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        //mTabLayout= (SegmentTabLayout) v.findViewById(R.id.main);
        view=getActivity().getWindow().getDecorView();
        viewPager= (ViewPager) v.findViewById(R.id.main);
        mTabLayout = ViewFindUtils.find(view,R.id.tl_1);
        initData();
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.setCurrentItem(1);
        return v;
    }

    private void initData() {
        mFragments.add(new Recommend());
        mFragments.add(new Course());
        //设置tab的标题、选中图标、未选中图标
        String[] mTitles = {"推荐", "课程"};
        mTabLayout.setTabData(mTitles);
    }


}

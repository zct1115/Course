package com.example.zct11.course.ui.home.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.MypagerAdapter;
import com.example.zct11.course.bean.TabEntity;
import com.example.zct11.course.ui.home.fragment.Course;
import com.example.zct11.course.ui.home.fragment.Recommend;
import com.example.zct11.course.utils.ViewFindUtils;
import com.example.zct11.course.widget.NoScrollViewPager;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/19.
 */

public class MainFragment extends Fragment {

    private SegmentTabLayout mTabLayout;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"推荐", "课程"};



    public static MainFragment getInstance (){
        MainFragment mainFragment=new MainFragment();
        return  mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        mTabLayout = ViewFindUtils.find(v,R.id.tl_1);
        initData();
        mTabLayout.setTabData(mTitles,getActivity(),R.id.main,mFragments);
        mTabLayout.setCurrentTab(0);
        return v;
    }

    private void initData() {

        mFragments.add(Recommend.getInstance());
        mFragments.add(Course.getInstance());
    }


}

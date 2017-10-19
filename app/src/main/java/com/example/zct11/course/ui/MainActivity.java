package com.example.zct11.course.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.MypagerAdapter;
import com.example.zct11.course.bean.TabEntity;
import com.example.zct11.course.ui.home.mainfragment.DownLoadFragment;
import com.example.zct11.course.ui.home.mainfragment.MainFragment;
import com.example.zct11.course.ui.home.mainfragment.MessageFragment;
import com.example.zct11.course.ui.home.mainfragment.MyFragment;
import com.example.zct11.course.utils.ViewFindUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private CommonTabLayout mTabLayout;
    private MypagerAdapter adapter;
    private String[] mTitles = {"首页", "消息", "下载", "我的"};
    private View view;
    private ViewPager viewPager;

    //tab的标题、选中图标、未选中图标
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int[] mIconUnselectIds = {
            R.mipmap.home_notselect, R.mipmap.message_notselect,
            R.mipmap.download_notselect, R.mipmap.my_notselect};
    private int[] mIconSelectIds = {
            R.mipmap.home_select, R.mipmap.message_select,
            R.mipmap.download_select, R.mipmap.my_select};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view=getWindow().getDecorView();
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = ViewFindUtils.find(view,R.id.tl);
        initData();
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.setAdapter(new MypagerAdapter(getSupportFragmentManager(),mFragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(1);
        //设置红点
        mTabLayout.showDot(3);
    }


    private void initData() {
        mFragments.add(MainFragment.getInstance());
        mFragments.add(MessageFragment.getInstance());
        mFragments.add(DownLoadFragment.getInstance());
        mFragments.add(MyFragment.getInstance());
        //设置tab的标题、选中图标、未选中图标
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
    }
}

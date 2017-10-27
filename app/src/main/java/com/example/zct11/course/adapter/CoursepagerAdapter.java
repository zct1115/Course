package com.example.zct11.course.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/19.
 */

public class CoursepagerAdapter extends FragmentPagerAdapter {

     private ArrayList<Fragment> mFragments;



    public CoursepagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        this.mFragments=mFragments;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }
}

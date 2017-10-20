package com.example.zct11.course.widget;

import android.content.Context;
import android.support.annotation.Px;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zct11 on 2017/10/20.
 */

public class NoScrollViewPager extends ViewPager {

    private boolean noScroll = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public void scrollBy(@Px int x, @Px int y) {
        super.scrollBy(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (noScroll) return false;
        else
            return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(ev);
    }
}

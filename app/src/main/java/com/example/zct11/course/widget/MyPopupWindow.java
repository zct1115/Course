package com.example.zct11.course.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.zct11.course.R;

/**
 * Created by zct11 on 2017/12/15.
 */

public class MyPopupWindow extends PopupWindow {
    private View customView;
    private Context context;

    public MyPopupWindow(Context context,View customView) {
        super(context);
        this.context=context;
        this.customView = customView;
        setPopupWindow();
    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(customView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.mypopwindow_anim_style);// 设置动画
    }
}

package com.example.zct11.course.utils;

import android.widget.Toast;

import com.example.zct11.course.app.CourseApplication;

/**
 * Created by jingbin on 2016/12/14.
 * 单例Toast
 */

public class ToastUtil {

    private static Toast mToast;

    public static void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(CourseApplication.getInstance(), text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }
}

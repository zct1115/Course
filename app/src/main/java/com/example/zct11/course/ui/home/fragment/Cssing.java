package com.example.zct11.course.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;

/**
 * Created by Administrator on 2017/10/20.
 */

public class Cssing extends Fragment {

    public static Cssing getInstance (){
        Cssing cssing=new Cssing();
        return  cssing;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_cssing,null);
        return v;
    }
}

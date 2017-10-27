package com.example.zct11.course.ui.home.coursedetilsfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;

/**
 * Created by zct11 on 2017/10/23.
 */

public class Introduction extends Fragment {


    public static Introduction getInstance(){
        Introduction introduction=new Introduction();
        return introduction;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_introduction,null);
        return v;
    }
}

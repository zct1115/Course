package com.example.zct11.course.ui.home.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;

/**
 * Created by Administrator on 2017/10/19.
 */

public class DownLoadFragment extends Fragment {



    public static DownLoadFragment getInstance (){
        DownLoadFragment downLoadFragment=new DownLoadFragment();
        return  downLoadFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_download, null);
        return v;
    }
}

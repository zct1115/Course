package com.example.zct11.course.ui.home.mainfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zct11.course.R;
import com.example.zct11.course.ui.download.DownloadActivity;
import com.example.zct11.course.ui.download.HadDownloadActivity;
import com.example.zct11.course.ui.download.HistoryActivity;

/**
 * Created by Administrator on 2017/10/19.
 */

public class DownLoadFragment extends Fragment implements View.OnClickListener{

    private Toolbar toolbar;
    private LinearLayout loading;
    private LinearLayout loaded;
    private LinearLayout history;

    public static DownLoadFragment getInstance (){
        DownLoadFragment downLoadFragment=new DownLoadFragment();
        return  downLoadFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_download, null);
        toolbar= (Toolbar) v.findViewById(R.id.tool);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("我的视频");
        loading = (LinearLayout) v.findViewById(R.id.load);
        loaded = (LinearLayout) v.findViewById(R.id.loaded);
        history=(LinearLayout) v.findViewById(R.id.history);
        history.setOnClickListener(this);
        loaded.setOnClickListener(this);
        loading.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load:
                startActivity(new Intent(getActivity(), DownloadActivity.class));
                break;
            case R.id.loaded:
                startActivity(new Intent(getActivity(), HadDownloadActivity.class));
                break;
            case R.id.history:
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                break;
        }
    }
}

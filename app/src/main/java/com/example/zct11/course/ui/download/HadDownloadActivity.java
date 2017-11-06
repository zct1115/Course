package com.example.zct11.course.ui.download;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.DownloadAdapter;
import com.example.zct11.course.adapter.VideoAdapter;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.message.Downloadmessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import zlc.season.practicalrecyclerview.PracticalRecyclerView;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by zct11 on 2017/11/4.
 */

public class HadDownloadActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private PracticalRecyclerView recyclerView;
    private VideoAdapter mAdapter;
    private List<MyVideo> videos=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haddownload);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.tool);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("已缓存的视频");
           /*回退键触发事件*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAdapter=new VideoAdapter();
        recyclerView= (PracticalRecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        MyVideo myVideo=new MyVideo();
        myVideo.setTitle("zxc");
        myVideo.setSize("zxc");
        myVideo.setPath("zxc");
        videos.add(myVideo);
        mAdapter.addAll(videos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void getData(Downloadmessage downloadmessage){
        MyVideo myVideo=new MyVideo();
        myVideo.setTitle(downloadmessage.getName());
        myVideo.setSize(downloadmessage.getSize());
        myVideo.setPath(downloadmessage.getUrl());
        videos.add(myVideo);
        mAdapter.addAll(videos);
        mAdapter.notifyDataSetChanged();
    }
}

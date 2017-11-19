package com.example.zct11.course.ui.download;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.DownloadAdapter;
import com.example.zct11.course.adapter.VideoAdapter;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.database.DBManager;
import com.example.zct11.course.message.Downloadmessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by zct11 on 2017/11/4.
 */

public class HadDownloadActivity extends AppCompatActivity implements VideoAdapter.onItemClickListenr,View.OnClickListener{

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private VideoAdapter mAdapter;
    private List<MyVideo> videos=new ArrayList<>();

    private DBManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haddownload);
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
        mAdapter=new VideoAdapter(videos,this);
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        mAdapter.setOnItemClickListenr(this);
        LinearLayoutManager manager=new LinearLayoutManager(CourseApplication.getAppContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
        initData();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initData(){
        manager=new DBManager(this);
        List<Download>downloads=manager.getData();
        List<MyVideo> data=new ArrayList<>();
        if(downloads!=null){
            for (int i=0;i<downloads.size();i++){
                MyVideo myVideo=new MyVideo();
                myVideo.setPath(downloads.get(i).getUrl());
                myVideo.setSize(downloads.get(i).getSize());
                myVideo.setTitle(downloads.get(i).getName());
                data.add(myVideo);
            }
            videos.addAll(data);
            mAdapter.notifyDataSetChanged();
        }else {

        }
    }


    @Override
    public void onItemClick(int position) {
        JCVideoPlayerStandard.startFullscreen(this,JCVideoPlayerStandard.class,videos.get(position).getPath(),videos.get(position).getTitle());
    }

    @Override
    public void onClick(View v) {

    }
}

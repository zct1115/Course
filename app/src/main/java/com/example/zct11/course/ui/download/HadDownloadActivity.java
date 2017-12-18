package com.example.zct11.course.ui.download;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zct11.course.R;
//import com.example.zct11.course.adapter.DownloadAdapter;
import com.example.zct11.course.adapter.VideoAdapter;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.database.DBManager;
import com.example.zct11.course.message.Downloadmessage;
import com.example.zct11.course.message.EditDownload;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

/**
 * Created by zct11 on 2017/11/4.
 */

public class HadDownloadActivity extends AppCompatActivity implements VideoAdapter.onItemClickListenr, View.OnClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private VideoAdapter mAdapter;
    private LinearLayout deletelin;
    private List<MyVideo> videos = new ArrayList<>();
    private TextView nocheck;
    private CheckBox checkBox;
    private DBManager manager;
    private TextView delete;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haddownload);
        initView();
    }

    private void initView() {

        deletelin = (LinearLayout) findViewById(R.id.deletelin);
        mAdapter = new VideoAdapter(videos, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("已缓存的视频");
           /*回退键触发事件*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_more));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionbar_edit://
                        deletelin.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });

        nocheck = (TextView) findViewById(R.id.nocheck);
        nocheck.setOnClickListener(this);

        delete = (TextView) findViewById(R.id.delete);
        delete.setOnClickListener(this);
        mAdapter.setOnItemClickListenr(this);
        LinearLayoutManager manager = new LinearLayoutManager(CourseApplication.getAppContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initData() {
        manager = new DBManager(this);
        List<Download> downloads = manager.getData();
        List<MyVideo> data = new ArrayList<>();


        if (downloads != null) {
            for (int i = 0; i < downloads.size(); i++) {
                MyVideo myVideo = new MyVideo();
                myVideo.setPath(downloads.get(i).getPath());
                myVideo.setImg(downloads.get(i).getImg());
                myVideo.setSize(downloads.get(i).getSize());
                myVideo.setTitle(downloads.get(i).getName());
                myVideo.setId(downloads.get(i).getId());
                data.add(myVideo);
            }
            videos.addAll(data);
            mAdapter.notifyDataSetChanged();
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.down_menu, menu);
        return true;
    }


    @Override
    public void onItemClick(int position) {
        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, videos.get(position).getPath()+"/"+videos.get(position).getTitle(), videos.get(position).getTitle());
        //JCVideoPlayer.
    }

    @Override
    public void onLongClick(int position) {

    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.nocheck:
                mAdapter.setIsshowBox(true);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.delete:
                if (mAdapter.getMap().size() == 0) {
                    Toast.makeText(this, "请选择删除的条目", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(this).setMessage("确认删除？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                           Iterator<Integer> integers=mAdapter.getMap().keySet().iterator();
                           while (integers.hasNext()){
                               Integer key=integers.next();
                               for(int i=0;i<videos.size();i++){
                                   if(key==i){
                                       manager.delete(videos.get(i).getId());
                                       File file=new File(videos.get(i).getPath()+"/"+videos.get(i).getTitle());
                                       file.delete();
                                      // videos.get(i).getPath()
                                   }
                               }
                           }
                            videos.clear();
                            initData();
                            deletelin.setVisibility(View.GONE);
                            mAdapter.setIsshowBox(false);
                            mAdapter.notifyDataSetChanged();
                        }
                    }).setNegativeButton("取消", null).show();
                }

                break;
        }
    }


}

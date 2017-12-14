package com.example.zct11.course.ui.download;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.HistoryVideoAdapter;
import com.example.zct11.course.adapter.VideoAdapter;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.History;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.database.DBManager;
import com.example.zct11.course.database.HistoryDBManager;
import com.example.zct11.course.video.JCVideoPlayerStandardAutoCompleteAfterFullscreen;
import com.example.zct11.course.video.MyUserActionStandard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


/**
 * Created by zct11 on 2017/11/4.
 */

public class HistoryActivity extends AppCompatActivity implements HistoryVideoAdapter.onItemClickListenr, View.OnClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private HistoryVideoAdapter mAdapter;
    private LinearLayout deletelin;
    private List<History> videos = new ArrayList<>();
    private TextView nocheck;
    private CheckBox checkBox;
    private HistoryDBManager manager;
    private TextView delete;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
    }

    private void initView() {
        deletelin = (LinearLayout) findViewById(R.id.deletelin);
        mAdapter = new HistoryVideoAdapter(videos, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("历史纪录");
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
        JCVideoPlayer.releaseAllVideos();
    }

    private void initData() {
        manager = new HistoryDBManager(this);
        List<History> histories = manager.getData();
        List<History> data=new ArrayList<>();
        if (histories != null) {
            for (int i = 0; i < histories.size(); i++) {
                History history = new History(histories.get(i).getUrl(),histories.get(i).getTitle(),histories.get(i).getImg(),histories.get(i).getId());
                data.add(history);
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

        //JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandardAutoCompleteAfterFullscreen.class, videos.get(position).getUrl(), videos.get(position).getTitle());
        JCVideoPlayer.startFullscreen(this, JCVideoPlayerStandardAutoCompleteAfterFullscreen.class, videos.get(position).getUrl(), videos.get(position).getTitle());
    }


    @Override
    public void onLongClick(int position) {

    }

    @Override
    public void onClick(View v) {
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

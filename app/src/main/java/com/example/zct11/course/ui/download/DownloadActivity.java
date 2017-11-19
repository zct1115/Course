package com.example.zct11.course.ui.download;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.DownloadAdapter;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.client.NetworkUtil;
import com.example.zct11.course.message.Downloadmessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;
import zlc.season.rxdownload2.function.Utils;

/**
 * Created by zct11 on 2017/11/3.
 */

public class DownloadActivity extends AppCompatActivity {


    private PracticalRecyclerView recyclerView;
    private Toolbar toolbar;
    private DownloadAdapter mAdapter;
    private RxDownload rxDownload;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.tool);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("正在缓存的视频");
           /*回退键触发事件*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView= (PracticalRecyclerView)findViewById(R.id.recycler);
        rxDownload = RxDownload.getInstance(this);
        mAdapter = new DownloadAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapterWithLoading(mAdapter);
        loadData();
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Toast.makeText(this, "当前无网络，请检查网络状况", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        RxDownload.getInstance(this).getTotalDownloadRecords()
                .map(new Function<List<DownloadRecord>, List<DownloadItem>>() {
                    @Override
                    public List<DownloadItem> apply(List<DownloadRecord> downloadRecords) throws Exception {
                        List<DownloadItem> result = new ArrayList<>();
                        for (DownloadRecord each : downloadRecords) {
                            DownloadItem bean = new DownloadItem();
                            bean.record = each;
                            result.add(bean);
                        }
                        return result;
                    }
                })
                .subscribe(new Consumer<List<DownloadItem>>() {
                    @Override
                    public void accept(List<DownloadItem> downloadBeen) throws Exception {
                        mAdapter.addAll(downloadBeen);
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        List<DownloadItem> list = mAdapter.getData();
        for (DownloadItem each : list) {
            Utils.dispose(each.disposable);
        }
    }

    @Subscribe
    public void getData(Downloadmessage downloadmessage){
        MyVideo myVideo=new MyVideo();
        myVideo.setTitle(downloadmessage.getName());
        myVideo.setSize(downloadmessage.getSize());
        myVideo.setPath(downloadmessage.getUrl());
    }

}

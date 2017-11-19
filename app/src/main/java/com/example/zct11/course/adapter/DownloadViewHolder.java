package com.example.zct11.course.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.Download;
import com.example.zct11.course.database.DBManager;
import com.example.zct11.course.message.Downloadmessage;
import com.example.zct11.course.ui.download.DownloadController;
import com.example.zct11.course.ui.download.DownloadItem;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.io.File;


import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadStatus;
import zlc.season.rxdownload2.function.Utils;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static zlc.season.rxdownload2.function.Utils.dispose;
import static zlc.season.rxdownload2.function.Utils.empty;
import static zlc.season.rxdownload2.function.Utils.log;

/**
 * Created by zct11 on 2017/11/3.
 */

public class DownloadViewHolder extends AbstractViewHolder<DownloadItem> implements View.OnClickListener {

    private TextView mStatusText;
    private Button mActionButton;
    private ProgressBar mProgress;
    private TextView mPercent;
    private TextView mSize;
    private TextView mName;
    private AbstractAdapter mAdapter;
    private DownloadController mDownloadController;

    private DBManager dbManager;


    private LayoutInflater inflater;
    private Context mContext;
    private DownloadItem data;

    private RxDownload mRxDownload;
    private int flag;

    public DownloadViewHolder(ViewGroup parent, AbstractAdapter adapter) {
        super(parent, R.layout.download_manager_item);
        this.mAdapter = adapter;
        mContext = parent.getContext();
        initView();
        mRxDownload = RxDownload.getInstance(mContext);
        dbManager = new DBManager(mContext);
        mDownloadController = new DownloadController(mStatusText, mActionButton);
    }

    private void initView() {

        mStatusText = (TextView) itemView.findViewById(R.id.status);
        mActionButton = (Button) itemView.findViewById(R.id.action);
        mProgress = (ProgressBar) itemView.findViewById(R.id.progress);
        mName = (TextView) itemView.findViewById(R.id.name);
        mPercent = (TextView) itemView.findViewById(R.id.percent);
        mSize = (TextView) itemView.findViewById(R.id.size);
        mActionButton.setOnClickListener(this);
    }


    @Override
    public void setData(DownloadItem param) {
        this.data = param;

        String name = empty(param.record.getExtra2()) ? param.record.getSaveName() : param.record.getExtra2();
        mName.setText(name);


        Utils.log(data.record.getUrl());
        data.disposable = mRxDownload.receiveDownloadStatus(data.record.getUrl())
                .subscribe(new Consumer<DownloadEvent>() {
                    @Override
                    public void accept(DownloadEvent downloadEvent) throws Exception {
                        if (flag != downloadEvent.getFlag()) {
                            flag = downloadEvent.getFlag();
                            log(flag + "");
                        }

                        if (downloadEvent.getFlag() == DownloadFlag.FAILED) {
                            Throwable throwable = downloadEvent.getError();
                            Log.w("TAG", throwable);
                        }
                        mDownloadController.setEvent(downloadEvent);
                        updateProgressStatus(downloadEvent.getDownloadStatus());

                    }
                });
    }


    private void updateProgressStatus(DownloadStatus status) {
        mProgress.setIndeterminate(status.isChunked);
        mProgress.setMax((int) status.getTotalSize());
        Log.d("DownloadViewHolder", "status.getTotalSize():" + status.getTotalSize());
        mProgress.setProgress((int) status.getDownloadSize());
        Log.d("DownloadViewHolder下载过程", "status.getDownloadSize():" + status.getDownloadSize());
        mPercent.setText(status.getPercent());
        Log.d("DownloadViewHolder进度", status.getPercent());
        mSize.setText(status.getFormatStatusString());
        Log.d("DownloadViewHolder下载速度", status.getFormatStatusString());
        if (status.getDownloadSize() == status.getTotalSize()) {
            Log.d("DownloadViewHolder", "路径" + data.record.getSavePath());
            Log.d("DownloadViewHolder", "名称" + data.record.getSaveName());
            EventBus.getDefault().post(new Downloadmessage(data.record.getSavePath(), data.record.getSaveName(), status.getDownloadSize() / 1024 + "M"));
            delete();
            Download download=new Download(data.record.getSavePath()+"/"+data.record.getId()+".mp4",data.record.getSaveName(),status.getDownloadSize() / 1024 + "KB");
            dbManager.insert(download);
        }

    }

    private void delete() {
        dispose(data.disposable);
        mRxDownload.deleteServiceDownload(data.record.getUrl(), true)
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mAdapter.remove(getAdapterPosition());
                    }
                })
                .subscribe();

    }

    private void showPopUpWindow(View view) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
        listPopupWindow.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,
                new String[]{"删除"}));
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) {
                    delete();
                    listPopupWindow.dismiss();
                }
            }
        });
        listPopupWindow.setWidth(300);
        listPopupWindow.setAnchorView(view);
        listPopupWindow.setModal(false);
        listPopupWindow.show();
    }

    @Override
    public void onClick(View v) {
        mDownloadController.handleClick(new DownloadController.Callback() {
            @Override
            public void startDownload() {
                start();
            }

            @Override
            public void pauseDownload() {
                pause();
            }

            @Override
            public void open() {
                delete();
            }
        });
    }

    private void open() {
        File[] files = mRxDownload.getRealFiles(data.record.getUrl());
        if (files != null) {
            /*Intent intent = openFile(files[0]);
            mContext.startActivity(intent);*/
        } else {
            Toast.makeText(mContext, "File not exists", Toast.LENGTH_SHORT).show();
        }
    }

    private void start() {
        RxPermissions.getInstance(mContext)
                .request(WRITE_EXTERNAL_STORAGE)
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            throw new RuntimeException("no permission");
                        }
                    }
                })
                .compose(mRxDownload.<Boolean>transformService(data.record.getUrl()))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(mContext, "下载开始", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void pause() {
        mRxDownload.pauseServiceDownload(data.record.getUrl()).subscribe();
    }

}

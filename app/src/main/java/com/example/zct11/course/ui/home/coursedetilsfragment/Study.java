package com.example.zct11.course.ui.home.coursedetilsfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zct11.course.R;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.bean.CustomMission;
import com.example.zct11.course.client.NetworkUtil;
import com.example.zct11.course.database.DBManager;
import com.example.zct11.course.message.IsDownload;
import com.example.zct11.course.ui.download.DownloadActivity;
import com.example.zct11.course.ui.download.DownloadItem;
import com.example.zct11.course.ui.download.HadDownloadActivity;
import com.example.zct11.course.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Downloading;
import zlc.season.rxdownload3.core.Failed;
import zlc.season.rxdownload3.core.Mission;
import zlc.season.rxdownload3.core.Normal;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.core.Suspend;

import static zlc.season.rxdownload3.helper.UtilsKt.dispose;

/**
 * Created by zct11 on 2017/10/23.
 */

public class Study extends Fragment implements View.OnClickListener {

    private ImageButton down;
    private AlertDialog mDialog;
    private Disposable disposable;
    private Status currentStatus = new Status();
    private boolean flog = false;
    private String url = "https://d.pcs.baidu.com/file/f1f61baf89a30ab34d71e5837f02caae?fid=3733671192-250528-184852326148573&time=1513671942&rt=pr&sign=FDTAERVCY-DCb740ccc5511e5e8fedcff06b081203-tcUdEIXY1%2BSrg895YYy5JMtCyUs%3D&expires=8h&chkv=1&chkbd=1&chkpc=et&dp-logid=8179749753638837366&dp-callid=0&r=636240564";
    private String title = "android";
    private String img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514205141&di=f50482fa23171b74fb3dd2f3700b65ed&imgtype=jpg&er=1&src=http%3A%2F%2Fi2.sinaimg.cn%2Fgm%2F2014%2F0901%2FU10515P115DT20140901113257.jpg";

    public static Study getInstance() {
        return new Study();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_study, null);
        down = (ImageButton) v.findViewById(R.id.down);
        down.setOnClickListener(this);
        create();
        return v;
    }

    @Override
    public void onClick(View v) {

        if (NetworkUtil.isNetworkAvailable(getContext())) {
            if(SPUtils.getSharedStringData(CourseApplication.getAppContext(),"isDownloading").equals("having")){
                down("正缓存中，请返回查看");
            }else if(SPUtils.getSharedStringData(CourseApplication.getAppContext(),"isDownloading").equals("had")){
                down("该视频已缓存，请返回查看");
            }else {
                start();
                down("正在为你下载。。。");
                SPUtils.setSharedStringData(CourseApplication.getAppContext(),"isDownloading","having");
            }

        } else {
            Toast.makeText(getActivity(), "请检查当前的网络是否连接！！！", Toast.LENGTH_SHORT).show();
        }

    }

    private void down(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage(name);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();
    }

    private void dispatchClick() {
        if (currentStatus instanceof Normal) {
            start();
        } else if (currentStatus instanceof Suspend) {
            start();
        } else if (currentStatus instanceof Failed) {
            start();
        } else if (currentStatus instanceof Downloading) {
            stop();
        }
    }

    private void create() {
        disposable = RxDownload.INSTANCE.create(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Status>() {
                    @Override
                    public void accept(Status status) throws Exception {
                        currentStatus = status;
                    }
                });
        flog = true;
    }

    private void start() {
        RxDownload.INSTANCE.start(url).subscribe();
    }

    private void stop() {
        RxDownload.INSTANCE.stop(url).subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose(disposable);
    }
}

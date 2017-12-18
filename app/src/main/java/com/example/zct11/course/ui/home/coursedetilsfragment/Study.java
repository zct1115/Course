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
import com.example.zct11.course.client.NetworkUtil;
import com.example.zct11.course.database.DBManager;
import com.example.zct11.course.message.IsDownload;
import com.example.zct11.course.ui.download.DownloadActivity;
import com.example.zct11.course.ui.download.HadDownloadActivity;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Downloading;
import zlc.season.rxdownload3.core.Failed;
import zlc.season.rxdownload3.core.Normal;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.core.Suspend;

import static zlc.season.rxdownload3.helper.UtilsKt.dispose;

/**
 * Created by zct11 on 2017/10/23.
 */

public class Study extends Fragment implements View.OnClickListener{

    private ImageButton down;
    private AlertDialog mDialog;
    private Disposable disposable;
    private Status currentStatus = new Status();
    private String url="https://d.pcs.baidu.com/file/d376574c694fbf87a2c16879f3bab202?fid=3733671192-250528-1118259804516888&time=1513517761&rt=pr&sign=FDTAERVCY-DCb740ccc5511e5e8fedcff06b081203-CNPYnTl%2Fz8ysya17QAVl2y02Fpo%3D&expires=8h&chkv=1&chkbd=1&chkpc=et&dp-logid=8138361981341811194&dp-callid=0&r=297084619";
    private String title="android";
    private String img="http://img.zcool.cn/community/01080755c1edaf32f87528a18e9840.jpg@900w_1l_2o_100sh.jpg";
    public static Study getInstance(){
        return new Study();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_study,null);
        down= (ImageButton) v.findViewById(R.id.down);
        down.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        if(NetworkUtil.isNetworkAvailable(getContext())){

            DBManager manager=new DBManager(getActivity());
            Log.d("Study", "manager.checked(url):" + manager.checked(url));

            down();
          /*
            if(manager.checked(url)){
              down();
            }else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("提示");
                builder1.setMessage("已下载该视频");
                builder1.setPositiveButton("前往查看", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                        startActivity(new Intent(getActivity(), HadDownloadActivity.class));
                    }
                });
                mDialog = builder1.create();
                mDialog.show(); ;
            }*/

        }else {
            Toast.makeText(getActivity(), "请检查当前的网络是否连接！！！", Toast.LENGTH_SHORT).show();
        }

    }

    private void down(){
        create();
        start();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage("正在为你下载");
        builder.setPositiveButton("前往查看", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.dismiss();
                startActivity(new Intent(getActivity(), DownloadActivity.class));
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

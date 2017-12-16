package com.example.zct11.course.ui.home.coursedetilsfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.Downloading;
import com.example.zct11.course.client.NetworkUtil;
import com.example.zct11.course.ui.download.DownLoadModel;
import com.example.zct11.course.ui.download.DownloadActivity;
import com.example.zct11.course.utils.FolderManager;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Status;

/**
 * Created by zct11 on 2017/10/23.
 */

public class Study extends Fragment implements View.OnClickListener{

    private ImageButton down;
    private AlertDialog mDialog;
    private Disposable disposable;
    private Status currentStatus = new Status();

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
            //new DownLoadModel(id).downLoad("http://img.zcool.cn/community/01080755c1edaf32f87528a18e9840.jpg@900w_1l_2o_100sh.jpg","android", FolderManager.getPhotoFolder().getPath());
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("提示");
            builder.setMessage("正在为你下载");
            builder.setPositiveButton("前往查看", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mDialog.dismiss();
                    create("http://img.zcool.cn/community/01080755c1edaf32f87528a18e9840.jpg@900w_1l_2o_100sh.jpg");
                    start("http://img.zcool.cn/community/01080755c1edaf32f87528a18e9840.jpg@900w_1l_2o_100sh.jpg");
                    //EventBus.getDefault().post(new Download("http://img.zcool.cn/community/01080755c1edaf32f87528a18e9840.jpg@900w_1l_2o_100sh.jpg","android","10M"));
                    startActivity(new Intent(getActivity(), DownloadActivity.class));
                }
            });
            mDialog = builder.create();
            mDialog.show();
        }else {
            Toast.makeText(getActivity(), "请检查当前的网络是否连接！！！", Toast.LENGTH_SHORT).show();
        }

    }

    private void create(String url) {
        disposable = RxDownload.INSTANCE.create(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Status>() {
                    @Override
                    public void accept(Status status) throws Exception {
                        currentStatus = status;
                        setProgress(status);
                    }
                });
    }
    private void start(String url) {
        RxDownload.INSTANCE.start(url).subscribe();
    }
    private void setProgress(Status status) {
        EventBus.getDefault().post(new Downloading((int)status.getTotalSize(),(int)status.getDownloadSize(),status.percent(),status.formatString()));
    }
}

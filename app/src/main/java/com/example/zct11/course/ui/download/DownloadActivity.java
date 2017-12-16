package com.example.zct11.course.ui.download;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zct11.course.R;
//import com.example.zct11.course.adapter.DownloadAdapter;
import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.client.NetworkUtil;
import com.example.zct11.course.message.Downloadmessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Downloading;
import zlc.season.rxdownload3.core.Failed;
import zlc.season.rxdownload3.core.Mission;
import zlc.season.rxdownload3.core.Normal;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.core.Succeed;
import zlc.season.rxdownload3.core.Suspend;
import zlc.season.rxdownload3.core.Waiting;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;
import zlc.season.rxdownload3.helper.UtilsKt;
/*import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;
import zlc.season.rxdownload2.function.Utils;*/

/**
 * Created by zct11 on 2017/11/3.
 */

public class DownloadActivity extends AppCompatActivity {


    private PracticalRecyclerView recyclerView;
    private Toolbar toolbar;
   // private DownloadAdapter mAdapter;
    //private RxDownload rxDownload;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
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
        //rxDownload = RxDownload.getInstance(this);
       // mAdapter = new DownloadAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setAdapterWithLoading(mAdapter);
       // loadData();
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Toast.makeText(this, "当前无网络，请检查网络状况", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void loadData() {
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
*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
       /* List<DownloadItem> list = mAdapter.getData();
        for (DownloadItem each : list) {
  //          Utils.dispose(each.disposable);
        }*/
    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getData(Download download){
        Log.d("DownloadActivity", download.getUrl());

    }
    /*static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Mission> data = new ArrayList<>();

        public void addData(List<Mission> data) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ViewHolderDownloadItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_holder_download_item, parent, false);
            return new ViewHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setData((CustomMission) data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            holder.onAttach();
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            holder.onDetach();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CustomMission customMission;
        private Disposable disposable;
        private Status currentStatus;

        private ViewHolderDownloadItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchClick();
                }
            });
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
            } else if (currentStatus instanceof Succeed) {
                install();
            } else if (currentStatus instanceof ApkInstallExtension.Installed) {
                open();
            }
        }


        public void setData(CustomMission customMission) {
            this.customMission = customMission;

            Picasso.with(itemView.getContext()).load(customMission.getImg()).into(binding.icon);
        }

        public void onAttach() {
            disposable = RxDownload.INSTANCE.create(customMission)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Status>() {
                        @Override
                        public void accept(Status status) throws Exception {
                            currentStatus = status;
                            setProgress(status);
                            setActionText(status);
                        }
                    });
        }

        private void setProgress(Status status) {
            binding.progressBar.setMax((int) status.getTotalSize());
            binding.progressBar.setProgress((int) status.getDownloadSize());

            binding.percent.setText(status.percent());
            binding.size.setText(status.formatString());
        }

        private void setActionText(Status status) {
            String text = "";
            if (status instanceof Normal) {
                text = "开始";
            } else if (status instanceof Suspend) {
                text = "已暂停";
            } else if (status instanceof Waiting) {
                text = "等待中";
            } else if (status instanceof Downloading) {
                text = "暂停";
            } else if (status instanceof Failed) {
                text = "失败";
            } else if (status instanceof Succeed) {
                text = "安装";
            } else if (status instanceof ApkInstallExtension.Installing) {
                text = "安装中";
            } else if (status instanceof ApkInstallExtension.Installed) {
                text = "打开";
            }
            binding.action.setText(text);
        }

        public void onDetach() {
            UtilsKt.dispose(disposable);
        }

        private void start() {
            RxDownload.INSTANCE.start(customMission).subscribe();
        }

        private void stop() {
            RxDownload.INSTANCE.stop(customMission).subscribe();
        }

        private void install() {
            RxDownload.INSTANCE.extension(customMission, ApkInstallExtension.class).subscribe();
        }

        private void open() {
            RxDownload.INSTANCE.extension(customMission, ApkOpenExtension.class).subscribe();
        }
    }*/

}

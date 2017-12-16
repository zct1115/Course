package com.example.zct11.course.app;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.example.zct11.course.base.BaseApplication;
import com.example.zct11.course.database.CustomSqliteActor;
import com.example.zct11.course.utils.FolderManager;

import zlc.season.rxdownload3.core.DownloadConfig;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;

/**
 * Created by zct11 on 2017/10/19.
 */

public class CourseApplication extends BaseApplication {

    private static CourseApplication courseApplication;

    public  static  CourseApplication getInstance(){
        return courseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        courseApplication=this;
        initTextSize();
        DownloadConfig.Builder builder = DownloadConfig.Builder.Companion.create(this)
                .enableDb(true)
                .setDbActor(new CustomSqliteActor(this))
                .setDefaultPath(FolderManager.getVideoFolder().getPath())
                .enableService(true)
                .enableNotification(true);

        DownloadConfig.INSTANCE.init(builder);
    }

    /**
     * 使其系统更改字体大小无效
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}

package com.example.zct11.course.ui.download;

import com.example.zct11.course.bean.CustomMission;

import io.reactivex.disposables.Disposable;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Created by zct11 on 2017/11/3.
 */

public class DownloadItem implements ItemType {
    private String url;
    private String path;
    private String title;
    private String img;

    public DownloadItem(String url, String path, String title,String img) {
        this.url = url;
        this.path = path;
        this.img = img;
        this.title = title;
    }

    public DownloadItem(String img) {
        this.img = img;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int itemType() {
        return 0;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

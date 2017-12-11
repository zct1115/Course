package com.example.zct11.course.bean;

/**
 * Created by zct11 on 2017/11/4.
 */

public class History {
    private String url;
    private String title;
    private String img;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public History(String url, String title, String img, String id) {
        this.url = url;
        this.title = title;
        this.img = img;
        this.id = id;
    }

    public History(String url, String title, String img) {
        this.url = url;
        this.title = title;
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

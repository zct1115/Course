package com.example.zct11.course.bean;

/**
 * Created by zct11 on 2017/11/4.
 */

public class Download {
    private String url;
    private String name;
    private String size;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Download(String url, String name, String size, String id) {
        this.url = url;
        this.name = name;
        this.size = size;
        this.id = id;
    }

    public Download(String url, String name, String size) {
        this.url = url;
        this.name = name;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Download{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}

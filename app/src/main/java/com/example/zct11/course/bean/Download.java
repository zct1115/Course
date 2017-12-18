package com.example.zct11.course.bean;

/**
 * Created by zct11 on 2017/11/4.
 */

public class Download {
    private String img;
    private String path;
    private String name;
    private String size;
    private String id;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Download(String img, String path, String name, String size) {
        this.img = img;
        this.path = path;
        this.name = name;
        this.size = size;
    }

    public Download(String img, String path, String name, String size, String id) {
        this.img = img;
        this.path = path;
        this.name = name;
        this.size = size;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

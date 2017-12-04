package com.example.zct11.course.bean;

import zlc.season.practicalrecyclerview.ItemType;

/**
 * Created by zct11 on 2017/11/4.
 */

public class MyVideo  implements ItemType {
    private String path;
    private String title;
    private String size;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public int itemType() {
        return 0;
    }
}

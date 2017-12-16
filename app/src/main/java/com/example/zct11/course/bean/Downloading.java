package com.example.zct11.course.bean;

/**
 * Created by zct11 on 2017/11/4.
 */

public class Downloading {
    private int max;
    private int progress;
    private String isdown;
    private String size;

    public Downloading(int max, int progress, String isdown, String size) {
        this.max = max;
        this.progress = progress;
        this.isdown = isdown;
        this.size = size;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getIsdown() {
        return isdown;
    }

    public void setIsdown(String isdown) {
        this.isdown = isdown;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

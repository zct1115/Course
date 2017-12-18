package com.example.zct11.course.message;

/**
 * Created by zct11 on 2017/12/17.
 */

public class DeleteDownload {
    private boolean delete;
    private int position;
    private String size;

    public DeleteDownload(boolean delete, int position,String size) {
        this.delete = delete;
        this.position = position;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}

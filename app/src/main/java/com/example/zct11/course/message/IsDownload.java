package com.example.zct11.course.message;

/**
 * Created by zct11 on 2017/11/30.
 */

public class IsDownload {
    private boolean edit;

    public IsDownload(boolean edit) {
        this.edit = edit;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}

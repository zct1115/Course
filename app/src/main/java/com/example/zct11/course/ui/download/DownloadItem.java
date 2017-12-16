package com.example.zct11.course.ui.download;

import io.reactivex.disposables.Disposable;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Created by zct11 on 2017/11/3.
 */

public class DownloadItem implements ItemType {
    public Disposable disposable;
    //public DownloadRecord record;

    @Override
    public int itemType() {
        return 0;
    }
}

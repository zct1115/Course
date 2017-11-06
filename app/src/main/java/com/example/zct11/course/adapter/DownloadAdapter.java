package com.example.zct11.course.adapter;
import android.content.Context;
import android.view.ViewGroup;


import com.example.zct11.course.ui.download.DownloadItem;

import zlc.season.practicalrecyclerview.AbstractAdapter;

/**
 * Created by zct11 on 2017/10/2.
 */

public class DownloadAdapter extends AbstractAdapter<DownloadItem,DownloadViewHolder> {

    @Override
    protected DownloadViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new DownloadViewHolder(parent, this);
    }

    @Override
    protected void onNewBindViewHolder(DownloadViewHolder holder, int position) {
        holder.setData(get(position));
    }
}

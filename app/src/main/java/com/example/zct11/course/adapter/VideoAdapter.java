package com.example.zct11.course.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.ui.download.DownloadItem;

import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Created by zct11 on 2017/10/2.
 */

public class VideoAdapter extends AbstractAdapter<MyVideo,VideoAdapter.MyVideoAdapter> {


    @Override
    protected MyVideoAdapter onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyVideoAdapter(parent,this);
    }

    @Override
    protected void onNewBindViewHolder(MyVideoAdapter holder, int position) {
           holder.setData(get(position));
    }

    public class MyVideoAdapter extends AbstractViewHolder<MyVideo>{

        private TextView name;
        private TextView size;
        private ImageView imageView;
        private MyVideo data;

        public MyVideoAdapter(ViewGroup parent, AbstractAdapter adapter) {
            super(parent, R.layout.myvideo_item);
            initView();
        }

        private void initView() {
            name= (TextView) itemView.findViewById(R.id.video_name);
            size=(TextView) itemView.findViewById(R.id.video_size);
            imageView=(ImageView) itemView.findViewById(R.id.video_img);
        }

        @Override
        public void setData(MyVideo mdata) {
            this.data=mdata;
            name.setText(mdata.getTitle());
            size.setText(mdata.getSize());
        }
    }
}

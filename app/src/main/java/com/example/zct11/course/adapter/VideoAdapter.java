package com.example.zct11.course.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.ui.download.DownloadItem;

import java.util.List;

import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Created by zct11 on 2017/10/2.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.videoViewHolder> {

    private List<MyVideo> data;
    private Context context;
    private LayoutInflater inflater;
    private onItemClickListenr mOnItemClickListenr;

    public VideoAdapter(List<MyVideo> data, Context context) {
        this.data = data;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public videoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new videoViewHolder(inflater.inflate(R.layout.myvideo_item,null));
    }

    @Override
    public void onBindViewHolder(videoViewHolder holder, int position) {
        if(holder instanceof videoViewHolder){
            setViewHolder((videoViewHolder)holder,position);
        }
    }

    private void setViewHolder(videoViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getTitle());
        holder.size.setText(data.get(position).getSize());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class videoViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView size;
        private ImageView imageView;
        private LinearLayout linearLayout;

        public videoViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.video_name);
            size=(TextView) itemView.findViewById(R.id.video_size);
            imageView=(ImageView) itemView.findViewById(R.id.video_img);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.video);
        }
    }

    public interface onItemClickListenr{
        void onItemClick(int position);
    }
    public void setOnItemClickListenr(onItemClickListenr onItemClickListenr){
        this.mOnItemClickListenr = onItemClickListenr;
    }
}

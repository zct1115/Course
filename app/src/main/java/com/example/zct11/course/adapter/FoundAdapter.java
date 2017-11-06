package com.example.zct11.course.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.GankIoDataBean;
import com.example.zct11.course.bean.MovingData;
import com.example.zct11.course.utils.ImageLoaderUtils;
import com.example.zct11.course.utils.TimeUtil;
import com.example.zct11.course.widget.webview.WebViewActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */

public class FoundAdapter extends RecyclerView.Adapter<FoundAdapter.CourseViewHolder> {

    private List<GankIoDataBean.ResultsBean> data;
    private Context context;
    private LayoutInflater inflater;
    private onItemClickListenr mOnItemClickListenr;//RecycleView监听事件


    public FoundAdapter(List<GankIoDataBean.ResultsBean> data, Context context) {
        this.data = data;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseViewHolder(inflater.inflate(R.layout.item_found,null));
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if(holder instanceof CourseViewHolder){
            setViewHolder((CourseViewHolder)holder,position);
        }
    }

    private void setViewHolder(CourseViewHolder holder, final int position) {
        if(data.get(position).getImages()!=null){
            ImageLoaderUtils.display(context,holder.img,data.get(position).getImages().get(0),R.mipmap.ic_image_loading1,R.mipmap.ic_image_loading1);
        }else {
            holder.img.setVisibility(View.GONE);
        }

        holder.title.setText(data.get(position).getDesc());
        holder.name.setText(data.get(position).getWho());
        holder.time.setText(TimeUtil.getTranslateTime(data.get(position).getPublishedAt()));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //WebViewActivity.loadUrl(context, data.get(position).getUrl(), "加载中...");
                mOnItemClickListenr.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView title;
        public TextView name;
        public TextView time;
        public LinearLayout linearLayout;

        public CourseViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.iv_android_pic);
            title= (TextView) itemView.findViewById(R.id.tv_android_des);
            name= (TextView) itemView.findViewById(R.id.tv_android_who);
            time= (TextView) itemView.findViewById(R.id.tv_android_time);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.ll_all);
        }
    }

    /**
     * 监听接口
     */
    public interface onItemClickListenr {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListenr onItemClickListenr) {
        this.mOnItemClickListenr = onItemClickListenr;
    }

}

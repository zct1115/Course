package com.example.zct11.course.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.HtmlData;
import com.example.zct11.course.bean.MovingData;
import com.example.zct11.course.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */

public class CourseHtmlAdapter extends RecyclerView.Adapter<CourseHtmlAdapter.CourseViewHolder> {

    private List<HtmlData> data;
    private Context context;
    private LayoutInflater inflater;

    public CourseHtmlAdapter(List<HtmlData> data, Context context) {
        this.data = data;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseViewHolder(inflater.inflate(R.layout.item_course,null));
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if(holder instanceof CourseViewHolder){
            setViewHolder((CourseViewHolder)holder,position);
        }
    }

    private void setViewHolder(CourseViewHolder holder, int position) {
        ImageLoaderUtils.display(context,holder.img,data.get(position).getImg(),R.mipmap.ic_image_loading1,R.mipmap.ic_image_loading1);
        holder.title.setText(data.get(position).getTitle());
        holder.num.setText(data.get(position).getStep()+"·"+data.get(position).getNum()+"人在学");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView title;
        public TextView num;

        public CourseViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
            title= (TextView) itemView.findViewById(R.id.title);
            num= (TextView) itemView.findViewById(R.id.num);
        }
    }

}

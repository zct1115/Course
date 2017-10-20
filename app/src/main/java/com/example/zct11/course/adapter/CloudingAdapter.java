package com.example.zct11.course.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.MainRecommend;
import com.example.zct11.course.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */

public class CloudingAdapter extends RecyclerView.Adapter<CloudingAdapter.MainViewHolder> {

    private List<MainRecommend> data;
    private Context context;
    private LayoutInflater inflater;

    public CloudingAdapter(List<MainRecommend> data, Context context) {
        this.data = data;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(inflater.inflate(R.layout.layout_recommend,null));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        if(holder instanceof MainViewHolder){
            setViewHolder((MainViewHolder)holder,position);
        }
    }

    private void setViewHolder(MainViewHolder holder, int position) {
        ImageLoaderUtils.display(context,holder.img,data.get(position).getImg());
        /*new GlideImageLoader().displayImage(context,data.get(position).getImg(),);*/
        holder.title.setText(data.get(position).getTitle());
        holder.num.setText(data.get(position).getNum()+"人在学");
        holder.step.setText(data.get(position).getStep());
        holder.type.setText(data.get(position).getType());
       /* holder.price.setText("￥"+data.get(position).getPrice());*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView title;
     /*   public TextView price;*/
        public TextView step;
        public TextView type;
        public TextView num;

        public MainViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
            title= (TextView) itemView.findViewById(R.id.title);
            type= (TextView) itemView.findViewById(R.id.type);
          /*  price= (TextView) itemView.findViewById(R.id.price);*/
            step= (TextView) itemView.findViewById(R.id.step);
            num= (TextView) itemView.findViewById(R.id.num);
        }
    }

}

package com.example.zct11.course.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.MyVideo;
import com.example.zct11.course.ui.download.DownloadItem;
import com.example.zct11.course.utils.ImageLoaderUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private boolean isshowBox=false;

    public boolean isIsshowBox() {
        return isshowBox;
    }

    public void setIsshowBox(boolean isshowBox) {
        this.isshowBox = isshowBox;
    }

    private Map<Integer,Boolean> map=new HashMap<>();

    public VideoAdapter(List<MyVideo> data, Context context) {
        this.data = data;
        this.context = context;
        inflater=LayoutInflater.from(context);
        initMap();
    }

    private void initMap() {
        if(data.size()!=0){
            for (int i = 0; i < data.size(); i++) {
                map.put(i, false);
            }
        }


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

    private void setViewHolder(final videoViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getTitle());
        holder.size.setText(data.get(position).getSize());
        ImageLoaderUtils.displayBigPhoto(context,holder.imageView,data.get(position).getImg());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenr.onItemClick(position);
            }
        });
        if (isshowBox) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
        //设置checkBox改变监听
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用map集合保存
                if (isChecked){
                    map.put(position, isChecked);
                }else {
                    map.remove(position);
                }

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
        private CheckBox checkBox;

        public videoViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.video_name);
            size=(TextView) itemView.findViewById(R.id.video_size);
            imageView=(ImageView) itemView.findViewById(R.id.video_img);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.video);
            checkBox= (CheckBox) itemView.findViewById(R.id.check);
        }
    }

    //返回集合给MainActivity
    public Map<Integer, Boolean> getMap() {
        return map;
    }
    //设置是否显示CheckBox
    public void setShowBox() {
        //取反
        isshowBox = !isshowBox;
    }

    //点击item选中CheckBox
    public void setSelectItem(int position) {
        //对当前状态取反
        if (map.get(position)) {
            map.put(position, false);
        } else {
            map.put(position, true);
        }
        notifyItemChanged(position);
    }


    public interface onItemClickListenr{
        void onItemClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListenr(onItemClickListenr onItemClickListenr){
        this.mOnItemClickListenr = onItemClickListenr;
    }
}

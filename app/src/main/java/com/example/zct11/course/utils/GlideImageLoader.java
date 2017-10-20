package com.example.zct11.course.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zct11.course.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by zct11 on 2017/10/20.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object url, ImageView imageView) {
        Glide.with(context).load(url)
                .placeholder(R.mipmap.img_two_bi_one)
                .error(R.mipmap.img_two_bi_one)
                .crossFade(1000)
                .centerCrop()
                .into(imageView);
    }
}

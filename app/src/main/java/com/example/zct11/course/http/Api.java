package com.example.zct11.course.http;

import com.example.zct11.course.bean.GankIoDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by zct11 on 2017/11/1.
 */

public interface Api {
    public static String URL="http://gank.io/api/";


    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{page}/{pre_page}")
    Observable<GankIoDataBean> getGankIoData(@Path("type") String id, @Path("page") int page, @Path("pre_page") int pre_page);

}

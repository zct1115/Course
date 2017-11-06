package com.example.zct11.course.ui.found.model;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.bean.GankIoDataBean;
import com.example.zct11.course.client.RetrofitClient;
import com.example.zct11.course.http.Api;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
/**
 * Created by zct11 on 2017/11/1.
 */

public class FoundModel implements FoundModelImp {
    @Override
    public Observable<List<GankIoDataBean.ResultsBean>> getData(String id, int page, int pre_page) {
        RetrofitClient retrofitClient=RetrofitClient.getInstance(CourseApplication.getAppContext(), Api.URL);
        Api api=retrofitClient.create(Api.class);
        return api.getGankIoData(id, page, pre_page).map(new Function<GankIoDataBean, List<GankIoDataBean.ResultsBean>>() {
            @Override
            public List<GankIoDataBean.ResultsBean> apply(GankIoDataBean gankIoDataBean) throws Exception {
                List<GankIoDataBean.ResultsBean> data=gankIoDataBean.getResults();
                return data;
            }
        }).compose(retrofitClient.schedulersTransformer);
    }
}

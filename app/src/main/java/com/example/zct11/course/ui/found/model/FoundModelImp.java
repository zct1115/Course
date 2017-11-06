package com.example.zct11.course.ui.found.model;

import com.example.zct11.course.bean.GankIoDataBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by zct11 on 2017/11/1.
 */

public interface FoundModelImp {

    Observable<List<GankIoDataBean.ResultsBean>> getData(String id, int page, int pre_page);
}

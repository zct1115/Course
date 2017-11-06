package com.example.zct11.course.ui.found.presenter;

import com.example.zct11.course.bean.GankIoDataBean;
import com.example.zct11.course.ui.found.model.FoundModel;
import com.example.zct11.course.ui.found.view.FoundView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zct11 on 2017/11/1.
 */

public class FoundPresenter implements FoundPresenterImp{

    private FoundModel model;
    private FoundView view;

    public FoundPresenter(FoundView view) {
        this.view = view;
        model=new FoundModel();
    }

    @Override
    public void getData(String id, int page, int pre_page) {
       model.getData(id,page,pre_page).subscribe(new Observer<List<GankIoDataBean.ResultsBean>>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(List<GankIoDataBean.ResultsBean> resultsBeans) {
             view.getData(resultsBeans);
           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onComplete() {

           }
       });
    }
}

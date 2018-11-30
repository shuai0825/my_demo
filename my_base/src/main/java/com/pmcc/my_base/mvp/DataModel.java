package com.pmcc.my_base.mvp;

import com.pmcc.my_base.ResponseBean;
import com.pmcc.my_base.net.RetrofitManager;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${zhangshuai} on 2018/9/10.
 * 2751157603@qq.com
 * 网络请求,获取数据
 */
public class DataModel {
    /**
     * post获取数据
     *
     * @param url
     * @param parap
     */
    public void postModel(String url, Map<String, Object> parap, Observer<ResponseBean> observer) {
        RetrofitManager.getInstance().getHttpService().postData(url, parap)
                .subscribeOn(Schedulers.io())//发射事件,io线程网络请求,多次指定时,第一次有效
                .observeOn(AndroidSchedulers.mainThread())//接收事件，ui线程处理
                .subscribe(observer);
    }

    /**
     * get获取数据
     *
     * @param url
     * @param parap
     */
    public void getModel(String url, Map<String, Object> parap, Observer<ResponseBean> observer) {
        RetrofitManager.getInstance().getHttpService().getData(url, parap)
                .subscribeOn(Schedulers.io())//发射事件,io线程网络请求,多次指定时,第一次有效
                .observeOn(AndroidSchedulers.mainThread())//接收事件，ui线程处理
                .subscribe(observer);
    }

}


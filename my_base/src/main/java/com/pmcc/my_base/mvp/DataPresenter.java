package com.pmcc.my_base.mvp;

import com.pmcc.my_base.ResponseBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ${zhangshuai} on 2018/9/10.
 * 2751157603@qq.com
 * presenter层数据处理，继承父类只能一个，实现可以多个
 */
public class DataPresenter extends BasePresenter<DataView> {


    private final DataModel dataModel;
    private HashMap<String, Disposable> urlCancles;

    /**
     * 初始化，并绑定view
     *
     * @param view
     */
    public DataPresenter(DataView view) {
        super(view);
        dataModel = new DataModel();

    }
    public void getDatas(final String url, Map<String, Object> parap) {
        dataModel.getModel(url, parap, new Observer<ResponseBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                urlCancles.put(url, disposable);
            }

            @Override
            public void onNext(ResponseBean responseBean) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                cancleUrl(url);
            }
        });
    }
    public void postDatas(final String url, Map<String, Object> parap) {
        dataModel.postModel(url, parap, new Observer<ResponseBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                urlCancles.put(url, disposable);
            }

            @Override
            public void onNext(ResponseBean responseBean) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                cancleUrl(url);
            }
        });
    }
    /**
     * 取消单个网络请求(断开通道，取消请求)
     *
     * @param url
     */
    public void cancleUrl(String url) {
        if (urlCancles != null && urlCancles.containsKey(url)) {
            if (!urlCancles.get(url).isDisposed()) {
                urlCancles.get(url).dispose();
                urlCancles.remove(url);
            }
        }
    }

    /**
     * 取消该页面的所有网络请求（断开通道,取消请求）
     */
    public void cancleUrls() {
        if (urlCancles != null && urlCancles.size() > 0) {
            Set<Map.Entry<String, Disposable>> sets = urlCancles.entrySet();
            for (Map.Entry<String, Disposable> s : sets) {
                if (!s.getValue().isDisposed()) {
                    s.getValue().dispose();
                }
            }
        }
        urlCancles.clear();
        urlCancles = null;
    }
}

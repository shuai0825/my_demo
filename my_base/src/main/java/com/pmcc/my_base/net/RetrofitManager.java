package com.pmcc.my_base.net;

import com.pmcc.my_base.config.APIConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by ${zhangshuai} on 2018/11/29.
 * 2751157603@qq.com
 */
public class RetrofitManager {
    private static RetrofitManager mInstance;
    private Retrofit retrofit = null;
    private static final long DEFAULT_TIMEOUT = 60L;

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    OkHttpClient mClient = new OkHttpClient.Builder()
//                          添加离线缓存
//                            .cache(RetrofitConfig.fileCache)
//                            .addInterceptor(RetrofitConfig.addCacheInterceptor())
                            .addInterceptor(RetrofitConfig.addQueryParameterInterceptor())
                            .addInterceptor(RetrofitConfig.addLoggingInterceptor())//添加请求拦截(可以在此处打印请求信息和响应信息)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(APIConstant.baseUrl)//基础URL 建议以 / 结尾
                            .addConverterFactory(FastJsonConverterFactory.create())//设置 Json 转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
                            .client(mClient)
                            .build();
                }
            }
        }
        return retrofit;
    }
    public HttpService getHttpService(){
        return getRetrofit().create(HttpService.class);
    }

}

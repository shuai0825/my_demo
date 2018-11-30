package com.pmcc.my_base.net;

import com.pmcc.my_base.ResponseBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ${zhangshuai} on 2018/11/29.
 * 2751157603@qq.com
 */
public interface HttpService {
    /**
     * 普通的get请求
     *
     * @param url
     * @return
     */
    @GET
    Observable<ResponseBean> getData(@Url String url, @QueryMap Map<String, Object> map);

    /**
     * 普通的post请求
     *
     * @param url
     * @param map
     * @return
     */

    @POST
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    Observable<ResponseBean> postData(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 文件的下载
     *Observable是与rxjava的结合
     * @param url
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downFile(@Url String url);
    /**
     * 文件的端点下载
     *Observable是与rxjava的结合
     * @param url
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downTerminalFile(@Header("Range") String range, @Url String url);

    /**
     * 文件的上传
     *
     * @param url
     * @param map
     * @return
     */
    @POST
    @Multipart
    Observable<ResponseBean> postFile(@Url String url, @PartMap Map<String, RequestBody> map);

    @POST
    @Multipart
    Observable<ResponseBean> postOneFile(@Url String url, @FieldMap Map<String, Object> map, MultipartBody.Part file);
}

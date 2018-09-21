package com.jing.rxandroiddemo.model.api;

import com.jing.rxandroiddemo.model.entity.BaseBean;
import com.jing.rxandroiddemo.model.entity.User;

import java.io.InputStream;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

import static com.jing.rxandroiddemo.model.api.ApiConstant.DOWNLOAD;
import static com.jing.rxandroiddemo.model.api.ApiConstant.LOGIN;

/**
 * 存放所有的服务器访问接口
 * Created by Jessica on 2018/5/25.
 */
public interface ApiService {
    @POST(LOGIN)
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    Flowable<BaseBean<User>> login(@Body RequestBody body
            /*@Field("account") String account,
                                 @Field("password") String password*/);

    @GET(DOWNLOAD)
    @Streaming/*大文件需要加入这个判断，防止下载过程中写入到内存中*/
    Flowable<ResponseBody> download();
}

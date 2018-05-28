package com.jing.rxandroiddemo.model.api;

import com.jing.rxandroiddemo.model.entity.BaseBean;
import com.jing.rxandroiddemo.model.entity.User;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

import static com.jing.rxandroiddemo.model.api.ApiConstant.LOGIN;

/**
 * 存放所有的服务器访问接口
 * Created by Jessica on 2018/5/25.
 */
public interface ApiService {
    @POST(LOGIN)
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    Observable<BaseBean<User>> login(@Body RequestBody body
            /*@Field("account") String account,
                                 @Field("password") String password*/);
}

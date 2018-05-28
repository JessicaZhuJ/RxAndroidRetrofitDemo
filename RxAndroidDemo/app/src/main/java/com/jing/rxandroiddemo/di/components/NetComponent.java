package com.jing.rxandroiddemo.di.components;

import com.jing.rxandroiddemo.di.modules.NetModule;
import com.jing.rxandroiddemo.model.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 网络访问组件
 * 组件在注册后需要编译才能使用功能
 * Created by Jessica on 2018/5/25.
 */
@Component(modules = NetModule.class)
@Singleton//生成单例实例
public interface NetComponent {
    ApiService getApiService();

    Retrofit getRetrofit();

    OkHttpClient getOkHttpClient();

}

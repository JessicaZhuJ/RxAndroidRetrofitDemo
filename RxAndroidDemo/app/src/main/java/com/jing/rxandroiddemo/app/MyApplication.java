package com.jing.rxandroiddemo.app;

import android.app.Application;
import android.content.Context;

import com.jing.rxandroiddemo.di.components.DaggerNetComponent;
import com.jing.rxandroiddemo.di.components.NetComponent;
import com.jing.rxandroiddemo.di.modules.NetModule;

/**
 * application
 * Created by Jessica on 2018/5/25.
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    private NetComponent netComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initNetComponent();
        initDataBase();
    }

    private void initDataBase() {

    }

    /**
     * -private
     * 初始化网络访问控件
     */
    private void initNetComponent() {
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}

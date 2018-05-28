package com.jing.rxandroiddemo.di.modules;

import com.jing.rxandroiddemo.presenter.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jessica on 2018/5/28.
 */
@Module
public class LoginModule {
    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @Provides
    LoginContract.View provideView() {
        return view;
    }
}

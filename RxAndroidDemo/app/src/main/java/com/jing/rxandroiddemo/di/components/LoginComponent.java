package com.jing.rxandroiddemo.di.components;

import com.jing.rxandroiddemo.di.modules.LoginModule;
import com.jing.rxandroiddemo.di.scopes.UserScope;
import com.jing.rxandroiddemo.view.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jessica on 2018/5/28.
 */
@Component(modules = LoginModule.class, dependencies = NetComponent.class)
@UserScope
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}

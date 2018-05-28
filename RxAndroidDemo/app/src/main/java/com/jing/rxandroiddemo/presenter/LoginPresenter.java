package com.jing.rxandroiddemo.presenter;

import android.util.Log;

import com.jing.rxandroiddemo.model.api.ApiService;
import com.jing.rxandroiddemo.model.entity.BaseBean;
import com.jing.rxandroiddemo.model.entity.User;
import com.jing.rxandroiddemo.util.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Jessica on 2018/5/28.
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private ApiService apiService;

    @Inject
    public LoginPresenter(LoginContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void login(String account, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account", account);
            jsonObject.put("password", MD5Util.encrypt(password));
        } catch (JSONException e) {
            Log.e("login", "login: " + e.getMessage(), e);
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        apiService.login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<BaseBean<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("login", "onError: " + e.getMessage(), e);
                        view.loginFail(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseBean<User> userBaseBean) {
                        if (userBaseBean != null) {
                            if (userBaseBean.getErrCode() == 999) {
                                view.saveAccountMsg();
                            }
                            view.loginFail(userBaseBean.getMsg());
                        } else {
                            view.loginFail("service return null");
                        }
                    }
                });
    }
}

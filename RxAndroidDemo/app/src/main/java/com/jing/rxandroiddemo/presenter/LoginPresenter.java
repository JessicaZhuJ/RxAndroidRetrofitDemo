package com.jing.rxandroiddemo.presenter;

import android.util.Log;

import com.jing.rxandroiddemo.model.api.ApiService;
import com.jing.rxandroiddemo.model.entity.BaseBean;
import com.jing.rxandroiddemo.model.entity.User;
import com.jing.rxandroiddemo.util.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InputStream;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

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
//                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    view.loginFail("服务器返回错误");
                    return new BaseBean<>();
                })
                .subscribe(new Subscriber<BaseBean<User>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(BaseBean<User> userBaseBean) {
                        if (userBaseBean != null) {
                            if (userBaseBean.getErrCode() == 999L) {
                                view.saveAccountMsg();
                            }
                            view.loginFail(userBaseBean.getMsg());
                        } else {
                            view.loginFail("service return null");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("login", "onError: " + e.getMessage(), e);
                        view.loginFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void download(String filepath) {
        apiService.download()
                .unsubscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, InputStream>() {
                    @Override
                    public InputStream apply(ResponseBody responseBody) throws Exception {
                        return responseBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation())
                .doOnNext(inputStream -> {
                    // write to file
                })
                .unsubscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InputStream>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(InputStream inputStream) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

package com.jing.rxandroiddemo.presenter;

/**
 * Created by Jessica on 2018/5/28.
 */

public class LoginContract {
    public interface View extends BaseContract.View {
        void loginSuccess();

        void loginFail(String msg);

        void saveAccountMsg();
    }

    interface Presenter {
        void login(String account, String password);
    }
}

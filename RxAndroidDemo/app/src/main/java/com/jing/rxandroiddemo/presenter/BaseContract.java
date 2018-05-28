package com.jing.rxandroiddemo.presenter;

/**
 * Created by Jessica on 2018/5/28.
 */

public class BaseContract {
    public interface View {
        void showMessage(String msg);

        void showLoading();

        void hideLoading();
    }
}

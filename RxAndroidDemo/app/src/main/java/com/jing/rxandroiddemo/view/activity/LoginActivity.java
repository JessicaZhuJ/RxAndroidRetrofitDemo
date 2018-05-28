package com.jing.rxandroiddemo.view.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jing.rxandroiddemo.R;
import com.jing.rxandroiddemo.app.MyApplication;
import com.jing.rxandroiddemo.di.components.DaggerLoginComponent;
import com.jing.rxandroiddemo.di.modules.LoginModule;
import com.jing.rxandroiddemo.presenter.LoginContract;
import com.jing.rxandroiddemo.presenter.LoginPresenter;
import com.jing.rxandroiddemo.util.Constant;
import com.jing.rxandroiddemo.util.SPUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Jessica on 2018/5/28.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.rember_pass)
    CheckBox remberPass;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick(R.id.btn_login)
    public void onViewClick() {
        String account = etUser.getText().toString();
        String password = etPwd.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            showMessage("用户名密码不为空");
            return;
        }
        presenter.login(account, password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initComponent() {
        DaggerLoginComponent.builder()
                .netComponent(MyApplication.getInstance().getNetComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    protected void initData() {
        String account = (String) SPUtil.get(this, Constant.KEY_ACCOUNT, "");
        etUser.setText(account);
        boolean isChecked = (boolean) SPUtil.get(this, Constant.KEY_CHECKED, false);
        String password = (String) SPUtil.get(this, Constant.KEY_PASSWORD, "");
        if (isChecked) {
            etPwd.setText(password);
        }
        remberPass.setChecked(isChecked);
    }

    @Override
    public void loginSuccess() {
        showMessage("Login Success");
    }

    @Override
    public void loginFail(String msg) {
        showMessage(msg);
    }

    @Override
    public void saveAccountMsg() {
        SPUtil.put(this, Constant.KEY_ACCOUNT, etUser.getText().toString().trim());
        SPUtil.put(this, Constant.KEY_PASSWORD, etPwd.getText().toString().trim());
        SPUtil.put(this, Constant.KEY_CHECKED, remberPass.isChecked());
    }

    @Override
    public void showMessage(String msg) {
        runOnUiThread(() -> Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show());

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

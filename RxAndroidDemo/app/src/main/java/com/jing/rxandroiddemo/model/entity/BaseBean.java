package com.jing.rxandroiddemo.model.entity;

/**
 * 接收服务器返回数据
 * Created by Jessica on 2018/5/28.
 */
public class BaseBean<T> {
    private Integer errCode;
    private String msg;
    private T data;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "errCode=" + errCode +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

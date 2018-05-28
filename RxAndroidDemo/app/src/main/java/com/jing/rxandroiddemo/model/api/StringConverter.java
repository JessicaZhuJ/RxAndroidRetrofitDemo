package com.jing.rxandroiddemo.model.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Jessica on 2018/5/25.
 */

public class StringConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(@NonNull ResponseBody value) throws IOException {
        return value.string();
    }
}

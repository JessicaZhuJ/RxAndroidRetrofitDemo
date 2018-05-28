package com.jing.rxandroiddemo.model.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

/**
 * Created by Jessica on 2018/5/25.
 */

public class EntityUtils {
    private EntityUtils(){}
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateTime.class,new DateTimeTypeAdapter())
            .create();
}

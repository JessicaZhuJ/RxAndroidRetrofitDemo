package com.jing.rxandroiddemo.model.entity;

/**
 * 所有的实体类都包含的两个字段
 * Created by Jessica on 2018/5/28.
 */
public class BaseEntity {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

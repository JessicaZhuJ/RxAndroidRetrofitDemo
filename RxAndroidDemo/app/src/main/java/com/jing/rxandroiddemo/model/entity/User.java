package com.jing.rxandroiddemo.model.entity;

import java.util.List;

/**
 * 用户实体
 * Created by Jessica on 2018/5/28.
 */
public class User extends BaseEntity {
    /**
     * 工号
     */
    private String empNum;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号
     */
    private String account;
    /**
     *
     */
    private String department;
    /**
     * 手机
     */
    private String phone;
    /**
     * 手机短号
     */
    private String ezPhone;
    /**
     * sex:female女male男
     */
    private String sex;

    private List<String> roles;

    public User() {
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEzPhone() {
        return ezPhone;
    }

    public void setEzPhone(String ezPhone) {
        this.ezPhone = ezPhone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

package com.peng.desertcamel.user.entity;

import java.io.Serializable;

/**
 * Created by Peng
 * Time: 2018/10/29 12:10
 * 用户实体类
 */
public class User implements Serializable {

    //对应数据库表 t_user
    private String uid;//主键

    private String loginname;//登录名

    private String loginpass;//登录密码

    private String email;//邮箱

    private String phone;//电话
    private String address;//地址

    public User() {
    }

    public User(String loginname, String loginpass) {
        this.loginname = loginname;
        this.loginpass = loginpass;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", loginname='" + loginname + '\'' +
                ", loginpass='" + loginpass + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

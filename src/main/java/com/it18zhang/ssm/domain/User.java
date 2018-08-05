package com.it18zhang.ssm.domain;

import java.util.List;

/**
 * User
 * @author zong
 */
public class User {
    private Integer id ;//用户id，数据库主键
    private  String account;//用户账户名
    private  String password;//用户密码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

package com.mybatis.domain.user;

import java.io.Serializable;

/**
 * 用户model
 *
 * Created by yunkai on 2017/5/24.
 */
public class CalmWangUserModel implements Serializable{

    private Integer ID;

    private String userName; //用户名称

    private String userPhone;//用户联系方式

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}

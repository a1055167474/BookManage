package com.example.SpringProjectDemo.entity;

/**
 * @author qinzhibin
 * @description
 * @date 2021/3/31
 */
public class SystemUser {
    private String id;

    private String userName;

    private String userAccount;

    private String phone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

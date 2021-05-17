package com.example.SpringProjectDemo.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-03-17 15:26:33
 */
public class User implements Serializable {
    private static final long serialVersionUID = 243641949909704234L;
    /**
    * 人员id
    */
    private Long id;
    /**
    * 用户账号
    */
    private String account;
    /**
    * 用户姓名
    */
    private String name;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 用户角色（0-普通用户  1-管理员）
    */
    private Integer userRole;
    /**
    * 是否删除（0-未删除   1-删除）
    */
    private Integer isDeleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}
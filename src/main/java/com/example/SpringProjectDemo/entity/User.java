package com.example.SpringProjectDemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-05-17 17:02:39
 */
public class User implements Serializable {
    private static final long serialVersionUID = 750703543351551398L;
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
    * 用户电话
    */
    private String phone;
    /**
    * 用户角色（0-普通用户  1-管理员）
    */
    private Integer userRole;
    /**
    * 用户创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 账号状态（0-启用  1-停用）
     */
    private Integer state;
    /**
    * 是否删除（0-未删除   1-删除）
    */
    private Integer isDeleted;


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}
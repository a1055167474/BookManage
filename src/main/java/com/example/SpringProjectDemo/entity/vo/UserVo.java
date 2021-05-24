package com.example.SpringProjectDemo.entity.vo;

import com.example.SpringProjectDemo.entity.User;

/**
 * @author qinzhibin
 * @description
 * @date 2021/5/24
 */
public class UserVo extends User {

    //当前页码
    private int page;

    //每页显示数量
    private int size;

    private int start;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

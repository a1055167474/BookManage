package com.example.SpringProjectDemo.entity;

import com.example.SpringProjectDemo.common.CommonUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author chenzihan
 * @description 分页信息实体类
 * @date 2021/3/24
 */
public class Page implements Serializable {

    //查询页数
    private int page;

    //每页数据条数
    private int limit;

    private int start;

    private int total;

    private String orderBy;

    private String sort;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        if(limit <= 0){
            limit = 10;
        }
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getStart() {

        if(page <= 0){
            this.page = 0;
        }else {
            this.start = (page - 1) * limit;
        }
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOrderBy() {

        if(StringUtils.isNotBlank(this.orderBy)){
            return CommonUtils.propertyToField(orderBy) + "" + this.getSort();
        }else {
            return null;
        }
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

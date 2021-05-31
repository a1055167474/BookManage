package com.example.SpringProjectDemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (BorrowReturn)实体类
 *
 * @author makejava
 * @since 2021-03-17 15:25:35
 */
public class BorrowReturn implements Serializable {
    private static final long serialVersionUID = -73365059004300473L;
    /**
    * 图书借出记录主键id
    */
    private Long id;
    /**
    * 借出人id
    */
    private Long userId;
    /**
    * 借用图书id
    */
    private Long bookId;
    /**
    * 借用图书数量
    */
    private Integer amount;
    /**
    * 借出时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    /**
    * 图书状态（0-借出未归还  1-借出已归还  2-挂失  3-挂失已处理）
    */
    private Integer state;
    /**
    * 归还时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date returnTime;

    /**
     * 是否删除（0-未删除  1-已删除）
     */
    private Integer isDeleted;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

}
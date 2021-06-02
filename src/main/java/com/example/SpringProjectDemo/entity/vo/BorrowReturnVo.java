package com.example.SpringProjectDemo.entity.vo;

import com.example.SpringProjectDemo.entity.BorrowReturn;

/**
 * @author chenzihan
 * @description 书籍借用记录传输类
 * @date 2021/3/19
 */
public class BorrowReturnVo extends BorrowReturn {

    //借用人姓名
    private String userName;

    //借用人联系方式
    private String phone;

    //图书名称
    private String bookName;

    //图书作者
    private String bookAuthor;

    //当前页码
    private int page;

    //每页显示数量
    private int size;

    private int start;

    //借阅数量（用于图书借阅排行榜）
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}

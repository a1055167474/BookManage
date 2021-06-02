package com.example.SpringProjectDemo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.entity.Book;
import com.example.SpringProjectDemo.entity.Page;

import java.util.List;

/**
 * (Book)表服务接口
 *
 * @author makejava
 * @since 2021-03-17 15:24:42
 */
public interface BookService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Book queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Book> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    Book insert(Book book);

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    Book update(Book book);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    List<Book> selectAllBook(Book book , Page page);


    /**
     * 查询图书总数量用于分页
     * @param book
     * @return
     */
    int selectAllBookCount(Book book);

    /**
     * 查询图书排行前5的图书信息
     * @return
     */
    JSONObject getBookTopFive();

    /**
     * 查询图书总量和种数
     * @return
     */
    JSONObject getBookCount();
}
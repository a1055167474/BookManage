package com.example.SpringProjectDemo.dao;

import com.example.SpringProjectDemo.entity.Book;
import com.example.SpringProjectDemo.entity.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Book)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-17 15:24:42
 */
public interface BookDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Book queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Book> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param book 实例对象
     * @return 对象列表
     */
    List<Book> queryAll(@Param("book") Book book, @Param("page") Page page);

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 影响行数
     */
    int insert(Book book);

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 影响行数
     */
    int update(Book book);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int queryAllCount(@Param("book") Book book);

    /**
     * 根据图书id列表查询图书信息
     * @param bookIds
     * @return
     */
    List<Book> getInfoByIds(@Param("bookIds") List<Long> bookIds);


    /**
     * 查询图书种数和总量
     * @return
     */
    List<Book> selectAll();
}
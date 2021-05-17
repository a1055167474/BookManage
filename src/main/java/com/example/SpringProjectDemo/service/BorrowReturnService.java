package com.example.SpringProjectDemo.service;

import com.example.SpringProjectDemo.entity.BorrowReturn;
import java.util.List;

/**
 * (BorrowReturn)表服务接口
 *
 * @author makejava
 * @since 2021-03-17 15:25:35
 */
public interface BorrowReturnService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BorrowReturn queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BorrowReturn> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param borrowReturn 实例对象
     * @return 实例对象
     */
    BorrowReturn insert(BorrowReturn borrowReturn);

    /**
     * 修改数据
     *
     * @param borrowReturn 实例对象
     * @return 实例对象
     */
    BorrowReturn update(BorrowReturn borrowReturn);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
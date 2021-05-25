package com.example.SpringProjectDemo.service;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.entity.Page;
import com.example.SpringProjectDemo.entity.vo.BorrowReturnVo;

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
     * @param borrowReturn 查询起始位置
     * @return 对象列表
     */
    List<BorrowReturn> queryAllByLimit(BorrowReturnVo borrowReturn);

    /**
     * 查询借用记录总条数
     * @param borrowReturn
     * @return
     */
    int queryTotal(BorrowReturnVo borrowReturn);


    /**
     * 新增数据
     *
     * @param borrowReturn 实例对象
     * @return 实例对象
     */
    Response<?> insert(BorrowReturn borrowReturn);

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

    /**
     * 图书丢失挂失
     * @param borrowReturnVo
     * @return
     */
    Response<?> lostReport(BorrowReturnVo borrowReturnVo);


    /**
     * 管理员处理挂失记录
     * @param borrowReturnVo
     * @return
     */
    Response<?> handleLostReport(BorrowReturnVo borrowReturnVo);


    /**
     * 获取挂失记录列表
     * @param borrowReturnVo
     * @return
     */
    List<BorrowReturn> getLostReportList(BorrowReturnVo borrowReturnVo);

    /**
     * 获取挂失记录总数
     * @param borrowReturnVo
     * @return
     */
    int getLostReportTotal(BorrowReturnVo borrowReturnVo);
}
package com.example.SpringProjectDemo.dao;

import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.entity.Page;
import com.example.SpringProjectDemo.entity.vo.BorrowReturnVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (BorrowReturn)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-17 15:25:35
 */
public interface BorrowReturnDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BorrowReturn queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BorrowReturn> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param borrowReturn 实例对象
     * @return 对象列表
     */
    List<BorrowReturnVo> queryAll(BorrowReturnVo borrowReturn);

    /**
     * 新增数据
     *
     * @param borrowReturn 实例对象
     * @return 影响行数
     */
    int insert(BorrowReturn borrowReturn);

    /**
     * 修改数据
     *
     * @param borrowReturn 实例对象
     * @return 影响行数
     */
    int update(BorrowReturn borrowReturn);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据用户Id获取当前用户的借阅记录
     * @param userId
     * @return
     */
    BorrowReturn selectByUserId(@Param("userId") Long userId);


    /**
     * 查询图书借用记录数量
     * @param borrowReturn
     * @return
     */
    Integer queryTotal(BorrowReturnVo borrowReturn);


    /**
     * 获取挂失记录列表
     * @param borrowReturnVo
     * @return
     */
    List<BorrowReturnVo> getLostReportList(BorrowReturnVo borrowReturnVo);

    /**
     * 获取挂失记录总数
     * @param borrowReturnVo
     * @return
     */
    int getLostReportTotal(BorrowReturnVo borrowReturnVo);

    /**
     * 根据用户id查询是否有借出或挂失记录未处理
     * @param userId
     * @return
     */
    List<BorrowReturn> selectByUserId2(@Param("userId") Long userId);

    /**
     * 查询图书排行前5的图书信息
     * @return
     */
    List<BorrowReturnVo> getBookTopFive();


}
package com.example.SpringProjectDemo.dao;

import com.example.SpringProjectDemo.entity.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * (Session)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-18 10:36:24
 */
public interface SessionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Session queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Session> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param session 实例对象
     * @return 对象列表
     */
    List<Session> queryAll(Session session);

    /**
     * 新增数据
     *
     * @param session 实例对象
     * @return 影响行数
     */
    int insert(Session session);

    /**
     * 修改数据
     *
     * @param session 实例对象
     * @return 影响行数
     */
    int update(Session session);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    //根据userId查询用户的登录session
    Session queryByUserId(@RequestParam("userId") Long userId);

    //根据sessionId获取session信息
    Session selectBySessionId(@RequestParam("sessionId") String sessionId);

    int deleteBySessionId(@RequestParam("sessionId") String sessionId);

}
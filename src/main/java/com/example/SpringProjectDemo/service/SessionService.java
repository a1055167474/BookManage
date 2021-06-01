package com.example.SpringProjectDemo.service;

import com.example.SpringProjectDemo.entity.Session;
import java.util.List;

/**
 * (Session)表服务接口
 *
 * @author makejava
 * @since 2021-05-18 10:36:24
 */
public interface SessionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Session queryById(Long id);

    /**
     * 修改数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    Session update(Session session);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    Session selectBySessionId(String sessionId);

    /**
     * 查询库中所有有效的session信息，并将超期的session置为失效
     * @return
     */
    int selectAllInvalid();
}
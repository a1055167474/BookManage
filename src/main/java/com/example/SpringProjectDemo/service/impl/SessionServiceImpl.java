package com.example.SpringProjectDemo.service.impl;

import com.example.SpringProjectDemo.entity.Session;
import com.example.SpringProjectDemo.dao.SessionDao;
import com.example.SpringProjectDemo.service.SessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Session)表服务实现类
 *
 * @author makejava
 * @since 2021-05-18 10:36:24
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {
    @Resource
    private SessionDao sessionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Session queryById(Long id) {
        return this.sessionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Session> queryAllByLimit(int offset, int limit) {
        return this.sessionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    @Override
    public Session insert(Session session) {
        this.sessionDao.insert(session);
        return session;
    }

    /**
     * 修改数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    @Override
    public Session update(Session session) {
        this.sessionDao.update(session);
        return this.queryById(session.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sessionDao.deleteById(id) > 0;
    }

    @Override
    public Session selectBySessionId(String sessionId) {
        return sessionDao.selectBySessionId(sessionId);
    }
}
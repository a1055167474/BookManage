package com.example.SpringProjectDemo.service.impl;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.entity.Session;
import com.example.SpringProjectDemo.dao.SessionDao;
import com.example.SpringProjectDemo.service.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 查询库中所有有效的session信息,将超期的session置为失效
     * @return
     */
    @Override
    public int selectAllInvalid() {
        List<Session> sessionList = sessionDao.selectAllInvalid();
        List<Long> ids = new ArrayList<>();
        for(Session s : sessionList){
            Long d = s.getCreateTime().getTime();
            Long d2 = new Date().getTime();
            //判断session信息是否超出规定时间
            int diffSeconds = (int) ((d2 - d) / 1000);
            if(diffSeconds > Const.SESSION_OUT_TIME) {
                ids.add(s.getId());
            }
        }
        if(CollectionUtils.isEmpty(ids)){
            return 0;
        }
        return sessionDao.updateByIds(ids);
    }
}
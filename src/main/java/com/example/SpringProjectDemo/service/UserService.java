package com.example.SpringProjectDemo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.entity.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2021-03-17 15:26:33
 */
@Service
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Response<?> insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Response<?> update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    /**
     * @Description: 根据条件查询用户信息
     */
    List<User> selectAllUser(UserVo user);

    /**
     * 根据sessionId查询用户信息
     */
    User selectByUserId(Long userId);

    /**
     * 获取用户数量用于分页
     * @param user
     * @return
     */
    int selectUserCount(UserVo user);


    /**
     * 逻辑删除用户信息
     * @param user
     * @return
     */
    Response<?> deleteUser(User user);


    /**
     * 近一周每日登陆人数
     *
     */
    JSONObject getWeekUserLogin();

    /**
     * 获取当前登录人数
     * @return
     */
    JSONObject getLoginCount();

    /**
     * 查询用户总数、普通用户、管理员数量
     *
     */
    JSONObject getUserCount();
}
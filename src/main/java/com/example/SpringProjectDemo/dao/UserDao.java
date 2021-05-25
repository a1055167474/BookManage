package com.example.SpringProjectDemo.dao;

import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-17 15:26:33
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(UserVo user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


    /**
     * 根据用户账号获取用户信息
     * @param account
     * @return
     */
    User getUserByAccount(String account);


    User selectByUserId(@Param("userId")Long userId);


    int queryCount(UserVo user);

    /**
     * 根据用户id集合查询用户信息
     * @param userIds
     * @return
     */
    List<User> getInfoByIds(List<Long> userIds);
}
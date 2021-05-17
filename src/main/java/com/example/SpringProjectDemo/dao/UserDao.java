package com.example.SpringProjectDemo.dao;

import com.example.SpringProjectDemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qinzhibin
 * @description TODO
 * @date 2021年03月23日
 */
@Mapper
public interface UserDao {

    //获取所有用户信息
    List<User> getAllUser();

    //根据用户昵称获取用户信息
    User getUserByName(@Param("name") String name);

    //新增用户
    int insert(User user);

    //根据用户名获取用户信息
    User getUserInfoByUserAccount(@Param("userAccount") String userAccount);

}

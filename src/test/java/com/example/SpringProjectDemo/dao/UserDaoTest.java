package com.example.SpringProjectDemo.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author qinzhibin
 * @description 用户类单元测试
 * @date 2021/3/26
 */
@SpringBootTest  //初始化SpringBoot上下文
@Transactional   //提供事务功能
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    //执行所有方法前都要执行的方法
    @BeforeEach
    void setUp() {
        System.out.println("-----------------------开始查询-----------------------");
    }

    //执行所有方法后都要执行的方法
    @AfterEach
    void tearDown() {
        System.out.println("-----------------------查询成功-----------------------");
    }

    @Test
    void getAllUser() {
        //通过断言测试，不会有提示
        assertNotNull(userDao.getAllUser(), "未获取到用户！");
    }

    @Test
    void getUserByName() {
        assertNotNull(userDao.getUserByName("AA"), "未获取到该用户信息");
    }
}
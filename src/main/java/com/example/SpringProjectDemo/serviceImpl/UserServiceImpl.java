package com.example.SpringProjectDemo.serviceImpl;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.dao.UserDao;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author qinzhibin
 * @description TODO
 * @date 2021年03月23日
 */


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getALlUser() {
        return userDao.getAllUser();
    }

    @Override
    public List<User> getAllUser(Integer page , Integer size){

        PageHelper.startPage(page,size);
        return userDao.getAllUser();
    }

    @Override
    public User getUserByName(String name) {
        User user = userDao.getUserByName(name);
        return user;
    }

    @Override
    public Response<?> addUser(User user) {

        //判断用户名是否重复
        User userInfo = userDao.getUserInfoByUserAccount(user.getUserAccount());
        if(userInfo != null ){
            return ResultUtils.ResultErrorUtil("用户名重复，请重新输入");
        }
        user.setIsDeleted(0);
        user.setCreateTime(new Date());
        int i = userDao.insert(user);
        if(i <= 0){
            return ResultUtils.ResultErrorUtil("新增用户失败");
        }
        logger.info("-------------------新增用户成功-------------------用户名：" + user.getUserAccount());
        return ResultUtils.ResultSuccessUtilMessage(null, "新增用户成功");
    }

    @Override
    public User getUser(User user) {
        return userDao.getUserInfoByUserAccount(user.getUserAccount());
    }
}

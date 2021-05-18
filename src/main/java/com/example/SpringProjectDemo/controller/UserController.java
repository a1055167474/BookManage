package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.Session;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.SessionService;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-03-17 15:26:34
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    /**
     * 删除用户信息
     *
     */
    @GetMapping("/getUserInfo")
    public Response<User> getUserInfo(HttpServletRequest request) {

        try{
            Cookie[] cookies = request.getCookies();
            String sessionId = null;
            if(cookies == null){
                return ResultUtils.ResultErrorUtil("未获取到Cookie信息");
            }
            for(Cookie c : cookies){
                if(c.getName().equals(Const.COOKIE_USER_NAME)){
                    sessionId = c.getValue();
                    break;
                }
            }
            if(StringUtils.isBlank(sessionId)){
                return ResultUtils.ResultErrorUtil("未获取到cookie信息");
            }
            //根据sessionId查询当前登录人的信息
            Session session1 = sessionService.selectBySessionId(sessionId);
            if(session1 == null){
                return ResultUtils.ResultErrorUtil("未登录！");
            }
            Long userId = session1.getUserId();
            //根据用户id查询用户信息
            User user = userService.selectByUserId(userId);
            if(user == null){
                return ResultUtils.ResultErrorUtil("用户不存在");
            }
            return ResultUtils.ResultSuccessUtilMessage(user,"查询当前用户成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询当前用户异常");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public User selectOne(Long id) {
        return this.userService.queryById(id);
    }

    /**
     * 获取所有用户信息
     *
     */
    @PostMapping("/selectAllUser")
    public Response<List<User>> selectAllUser(@RequestBody User user) {

        try{

            List<User> userList =  userService.selectAllUser(user);
            return ResultUtils.ResultSuccessUtilMessage(userList,"查询用户成功",userList.size());

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询用户异常");
    }

    /**
     * 保存用户信息
     *
     */
    @PostMapping("/updateUser")
    public Response<?> updateUser(@RequestBody User user) {

        try{

            if(user.getId() == null){
                return ResultUtils.ResultErrorUtil("未获取到用户id");
            }
            return userService.update(user);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询用户异常");
    }

    /**
     * 删除用户信息
     *
     */
    @PostMapping("/deleteUser")
    public Response<?> deleteUser(@RequestBody User user) {

        try{
            if(user.getId() == null){
                return ResultUtils.ResultErrorUtil("未获取到用户id");
            }

            userService.deleteById(user.getId());

            return ResultUtils.ResultSuccessUtilMessage(null,"查询用户成功");

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询用户异常");
    }

    /**
     * 新增用户信息
     *
     */
    @PostMapping("/addUser")
    public Response<?> addUser(@RequestBody User user) {

        try{
            if(StringUtils.isBlank(user.getAccount())){
                return ResultUtils.ResultErrorUtil("未获取到用户账号");
            }
            if(StringUtils.isBlank(user.getName())){
                return ResultUtils.ResultErrorUtil("未获取到用户姓名");
            }
            if(StringUtils.isBlank(user.getPhone())){
                return ResultUtils.ResultErrorUtil("未获取到用户电话");
            }
            if(StringUtils.isBlank(user.getPassword())){
                return ResultUtils.ResultErrorUtil("未获取到用户密码");
            }
            if(user.getUserRole() == null){
                return ResultUtils.ResultErrorUtil("未获取到用户角色");
            }

            return userService.insert(user);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("新增用户异常");
    }


}
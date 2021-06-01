package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.Session;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.entity.vo.UserVo;
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
import java.util.Calendar;
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
public class UserController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    /**
     * 查询当前登录用户信息
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
    public Response<List<User>> selectAllUser(@RequestBody UserVo user) {

        try{

            //例如前端传递的是2021-05-19，页面上显示的是2021-05-19，但实际上数据库中存储的可能是2021-05-19 16:00:00
            //需要对endTime天数加一
            if(user.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(user.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);
                user.setEndTime(c.getTime());
            }
            List<User> userList =  userService.selectAllUser(user);

            //查询用户总数，用于分页
            int count = userService.selectUserCount(user);
            return ResultUtils.ResultSuccessUtilMessage(userList,"查询用户成功", count);

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
            return userService.deleteUser(user);

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
            if(user.getState() == null){
                return ResultUtils.ResultErrorUtil("未获取到账号状态");
            }

            return userService.insert(user);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("新增用户异常");
    }


    /**
     * 查询当前登录用户角色
     * @param request
     * @return
     */
    @GetMapping("/checkUsePermission")
    public Response<?> checkUsePermission(HttpServletRequest request) {

        try {
            User user = getCurrentUser(request);
            if (user == null) {
                return ResultUtils.ResultErrorUtil("未获取到当前登录信息");
            }
            User user1 = userService.selectByUserId(user.getId());
            return ResultUtils.ResultSuccessUtilMessage(user1.getUserRole(), "查询用户角色成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询用户角色异常");
    }

    /**
     * 判断当前登录用户是否为管理员
     * @param request
     * @return
     */
    @GetMapping("/isManager")
    public Response<?> isManager(HttpServletRequest request) {

        try {
            User user = getCurrentUser(request);
            if (user == null) {
                return ResultUtils.ResultErrorUtil("未获取到当前登录信息");
            }
            Boolean flag = isManager(user);
            return ResultUtils.ResultSuccessUtilMessage(flag, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }

    /**
     * 账户启用
     * @param request
     * @return
     */
    @PostMapping("/userAccountOn")
    public Response<?> userAccountOn(@RequestBody User user, HttpServletRequest request) {

        try {
            User user1 = getCurrentUser(request);
            if (user1 == null) {
                return ResultUtils.ResultErrorUtil("未获取到当前登录信息");
            }
            boolean i = isManager(user1);
            if(!i){
                return ResultUtils.ResultErrorUtil("没有权限");
            }
            return userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("账号启用异常");
    }

}
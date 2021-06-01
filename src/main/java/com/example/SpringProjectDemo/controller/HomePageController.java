package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.Session;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.*;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chenzihan
 * @description 图书管理系统首页接口
 * @date 2021/4/1
 */
@RestController
@RequestMapping("/home")
public class HomePageController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private BookService bookService;

    @Resource
    private BorrowReturnService borrowReturnService;

    @Resource
    private LoginService loginService;

    @Resource
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
}

package com.example.SpringProjectDemo.controller;


import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.LoginService;
import com.example.SpringProjectDemo.service.SessionService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author chenzihan
 * @description 登录控制类
 * @date 2021/3/30
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;



    /**
     * 执行登录
     */
    @PostMapping("/login")
    @ResponseBody
    public Response<?> login(@RequestBody User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        if(StringUtils.isBlank(user.getAccount()) || StringUtils.isBlank(user.getPassword())){
            return ResultUtils.ResultErrorUtil("未获取到账号或密码");
        }
        try{

            return loginService.doLogin(user, session, request, response);

        }catch (Exception e){
            return ResultUtils.ResultErrorUtil("登录失败");
        }

    }


    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout" , method = RequestMethod.GET)
    public Response<?> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        try {

            return loginService.logout(session, request, response);

        } catch (Exception e) {
            return ResultUtils.ResultErrorUtil("退出登录失败");
        }
    }

}

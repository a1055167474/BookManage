package com.example.SpringProjectDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.config.RocketMq.content.UserContent;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.exception.BaseException;
import com.example.SpringProjectDemo.service.LoginService;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author qinzhibin
 * @description 登录控制类
 * @date 2021/3/30
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;


    /**
     * 执行登录
     */
    @PostMapping("/login")
    @ResponseBody
    public Response<?> login(@RequestBody User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        if(StringUtils.isBlank(user.getUserAccount()) || StringUtils.isBlank(user.getPassword())){
            return ResultUtils.ResultErrorUtil("未获取到登录名或密码");
        }
        try{

            Response<?> result = loginService.doLogin(user, session, request, response);
            return result;

        }catch (Exception e){
            logger.info("登录失败，当前登录用户为" + user.getUserAccount());
            return ResultUtils.ResultErrorUtil("登录失败");
        }

    }


    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public Response<?> getSession() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getSession().getAttribute("userInfo"));

        return ResultUtils.ResultSuccessUtilMessage(null, "退出登录成功");
    }


    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout" , method = RequestMethod.GET)
    public Response<?> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        try {

            Response<?> result = loginService.logout(session, request, response);
            return result;

        } catch (Exception e) {
            logger.info("退出登录异常");
            return ResultUtils.ResultErrorUtil("退出登录失败");
        }
    }

}

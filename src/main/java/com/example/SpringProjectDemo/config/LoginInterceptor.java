package com.example.SpringProjectDemo.config;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.dao.UserDao;
import com.example.SpringProjectDemo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author qinzhibin
 * @description 未登录拦截器
 * @date 2021/3/30
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("----------------开始进行登录拦截-----------------");

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // 获取HttpSession对象,如果session中没有用户信息，则进行登录重定向，到登录页面
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute(Const.SYSTEM_USER_SESSION);
//        if(userObj == null){
//            String path = request.getContextPath();
//            response.sendRedirect("http://localhost:7724/#/");
////            request.getRequestDispatcher("https://www.baidu.com").forward (request, response);
//            logger.info("用户信息不存在，进行登录重定向");
//            return false;
//        }
        return true;

//        if (null == cookies) {
//            response.sendRedirect(request.getContextPath() + "/");
//            logger.info("-----------未获取到cookies，进行登录重定向-----------" );
//            return false;
//        }
//        // 定义cookie_username，用户的一些登录信息，例如：用户名，密码等
//        String JSessionId = null;
//        // 获取cookie里面的一些用户信息
//        for (Cookie item : cookies) {
//            if (Const.COOKIE_USER_NAME.equals(item.getName())) {
//                JSessionId = item.getValue();
//                break;
//            }
//        }
//        // 如果cookie里面没有包含用户的一些登录信息，则重定向到登录界面
//        if (StringUtils.isEmpty(JSessionId)) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return false;
//        }
//
//        // 已经登录
//        return true;
    }
}


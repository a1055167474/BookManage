package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.dao.SessionDao;
import com.example.SpringProjectDemo.entity.Session;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.LoginService;
import com.example.SpringProjectDemo.service.SessionService;
import com.example.SpringProjectDemo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author chenzihan
 * @description
 * @date 2021/3/31
 */
@RestController
public class BaseController {

    @Resource
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    /**
     * 判断用户是否登录
     * @param request
     * @return
     */
    public Session checkLogin(HttpServletRequest request) {

        //获取cookie信息
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        if (cookies == null) {
            return null;
        }
        if (CollectionUtils.isEmpty(Arrays.asList(cookies))) {
            return null;
        }
        //获取cookie中的sessionId
        for (Cookie item : cookies) {
            if (Const.COOKIE_USER_NAME.equals(item.getName())) {
                sessionId = item.getValue();
                break;
            }
        }
        if (StringUtils.isNotBlank(sessionId)) {
            //查询sessionId是否有效
            Session session1 = sessionService.selectBySessionId(sessionId);
            if(session1 == null){
                //session 信息为空
                return null;
            }else {
                //session信息不为空判断，当前session是否超期
                Long d = session1.getCreateTime().getTime();
                Long d2 = new Date().getTime();
                //判断session信息是否超出规定时间
                int diffSeconds = (int) ((d2 - d) / 1000);
                if(diffSeconds > Const.SESSION_OUT_TIME){
                    //session信息已经失效，清除库中的session信息
                    sessionService.deleteById(session1.getId());
                    return null;
                }else {
                    //没有失效，刷新session的时间
                    session1.setCreateTime(new Date());
                    sessionService.update(session1);
                    return session1;
                }
            }
        }
        return null;
    }

    //根据请求获取当前登录人
    public User getCurrentUser(HttpServletRequest request){
        User user = new User();

        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        if(cookies == null){
            return user;
        }
        for(Cookie c : cookies){
            if(c.getName().equals(Const.COOKIE_USER_NAME)){
                sessionId = c.getValue();
                break;
            }
        }
        if(StringUtils.isBlank(sessionId)){
            return user;
        }
        //根据sessionId查询当前登录人的信息
        Session session1 = sessionService.selectBySessionId(sessionId);
        if(session1 == null){
            return user;
        }
        Long userId = session1.getUserId();

        return userService.selectByUserId(userId);
    }

    //判断当前登录用户是否为管理员
    public boolean isManager(User user){
        User user1 = userService.queryById(user.getId());
        return user1.getUserRole() == 1;
    }
}

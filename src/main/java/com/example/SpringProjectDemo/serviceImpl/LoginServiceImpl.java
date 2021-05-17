package com.example.SpringProjectDemo.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.dao.UserDao;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.LoginService;
import com.example.SpringProjectDemo.service.RedisService;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author qinzhibin
 * @description
 * @date 2021年03月23日
 */


@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;


    /**
     * 执行登录
     */
    public Response<?> doLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        String username = StringUtils.trim(user.getUserAccount());
        String password = StringUtils.trim(user.getPassword());
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ResultUtils.ResultErrorUtil("用户名或密码为空！");
        }
        User oldUser = userDao.getUserInfoByUserAccount(username);
        if (null == oldUser) {
            return ResultUtils.ResultErrorUtil("账号信息不存在！");
        }

        //密码加密解密
//        String newPassword = PasswordUtils.getMd5(password, username, user.getSalt());
        // 验证密码是否正确
        if (!password.equals(oldUser.getPassword())) {
            return ResultUtils.ResultErrorUtil("用户名或密码错误！");
        }

        // 将登录用户信息保存到session中
        session.setAttribute(Const.SYSTEM_USER_SESSION, oldUser);

        //当前会话维持时间，单位秒
        session.setMaxInactiveInterval(Integer.parseInt(Const.COOKIE_OUT_TIME));
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie(Const.COOKIE_USER_NAME, session.getId());
        // 设置cookie的持久化时间，单位：秒
        cookie_username.setMaxAge(Integer.parseInt(Const.COOKIE_OUT_TIME));
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);

        String key = Const.SESSION_KEY_HEAD + session.getId();
        //向redis中存储用户登录的sessionId和用户Id
        boolean a = redisService.set(key,oldUser.getId().toString());
        //设置session存储的有效时间
        long c = Long.parseLong(Const.SESSION_OUT_TIME);
        boolean b = redisService.expire(key,c);

        return ResultUtils.ResultSuccessUtilMessage(null, "登录成功");
    }

    @Override
    public Response<?> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 删除session里面的用户信息
//        session.removeAttribute(Const.SYSTEM_USER_SESSION);
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie("cookie_username", "");
        // 设置cookie的持久化时间，0
        cookie_username.setMaxAge(0);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);

        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        for (Cookie item : cookies) {
            if (Const.COOKIE_USER_NAME.equals(item.getName())) {
                sessionId = item.getValue();
                break;
            }
        }
        if(StringUtils.isNotBlank(sessionId)){
            String key = Const.SESSION_KEY_HEAD + sessionId;

            //将redis中保存的session信息清除
            redisService.del(key);
            return ResultUtils.ResultSuccessUtilMessage(null,"退出登录成功");
        }

        return ResultUtils.ResultErrorUtil("登录session不存在");
    }

}

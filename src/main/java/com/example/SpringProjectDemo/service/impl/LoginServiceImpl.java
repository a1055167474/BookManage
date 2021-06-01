package com.example.SpringProjectDemo.service.impl;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.dao.SessionDao;
import com.example.SpringProjectDemo.dao.UserDao;
import com.example.SpringProjectDemo.entity.Session;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.LoginService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author chenzihan
 * @description
 * @date 2021年03月23日
 */


@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDao userDao;

    @Resource
    private SessionDao sessionDao;

    @Override
    public Response<?> doLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        //查询当前用户名和密码是否正确
        User user1 =  userDao.getUserByAccount(user.getAccount());
        if(user1 == null){
            return ResultUtils.ResultErrorUtil("未查询到此用户，请联系管理员进行添加");
        }
        if(!user.getPassword().equals(user1.getPassword())){
            return ResultUtils.ResultErrorUtil("用户名或密码错误");
        }

        //判断当前账号信息是否已经停用
        if(user1.getState() == 1){
            return ResultUtils.ResultErrorUtil("账号已停用，请联系管理员进行激活");
        }

        Long userId = user1.getId();
        String sessionId = session.getId();

        //查询session表中该用户的登录信息是否有效
        Session session1 = sessionDao.queryByUserId(userId);
        if(session1 == null){
            //没有session信息，代表首次登陆，session 信息入库
            Session session2 = new Session();
            session2.setCreateTime(new Date());
            session2.setUserId(userId);
            session2.setSessionId(sessionId);
            session2.setState(0);
            sessionDao.insert(session2);
        }else{
            //session信息不为空，代表登陆过，更新登录信息
            session1.setSessionId(sessionId);
            session1.setCreateTime(new Date());
            session1.setState(0);
            sessionDao.update(session1);
        }

        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie(Const.COOKIE_USER_NAME, sessionId);
        // 设置cookie的持久化时间，单位：秒
        cookie_username.setMaxAge(Const.COOKIE_OUT_TIME);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);

        return ResultUtils.ResultSuccessUtilMessage(session1,"登录成功");
    }

    @Override
    public Response<?> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        //清除cookie信息
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie(Const.COOKIE_USER_NAME, "");
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
        //根据sessionId删除登录信息，将session 的状态置为1
        Session session1 = new Session();
        session1.setSessionId(sessionId);
        session1.setState(1);
        sessionDao.updateBySessionId(session1);

        return ResultUtils.ResultSuccessUtilMessage(null,"退出登录成功");
    }
}

package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.entity.SystemUser;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.RedisService;
import org.springframework.context.ApplicationContext;
import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.context.support.WebApplicationContextUtils;
import sun.net.httpserver.HttpsServerImpl;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author qinzhibin
 * @description
 * @date 2021/3/31
 */
@RestController
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private RedisService redisService;


    //研发云判断用户是否登录，本地不可用
    /*
    //    @Resource(name = "sessionRepository")
    private SessionRepository<ExpiringSession> sessionRepository;


    public SystemUser getSystemUser() {

        ExpiringSession es = getSession(Const.SESSION_HEADER);
        if (es != null) {
            SystemUser systemUser = new SystemUser();
            Map<String, Object> attr =
                    ((AttributePrincipal) es.getAttribute(Const.SYSTEM_USER)).getAttributes();
            try {
                BeanUtils.copyProperties(systemUser, attr);
            }catch (IllegalAccessError e){
                logger.error("校验用户登录异常：" + e);
            }
            return systemUser;
        }
        return null;
    }

    private ExpiringSession getSession(String name) {
        String sessionId = this.getSessionHeader(name);
        if (!StringUtils.isBlank(sessionId)) {
            if(null == sessionRepository){
                ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getRequest().getServletContext());
                sessionRepository = ac.getBean(SessionRepository.class);
            }
            ExpiringSession es = sessionRepository.getSession(sessionId);
            return es;
        }
        return null;
    }

    private String getSessionHeader(String name) {
        return this.getRequest().getHeader(name);
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }*/


//    protected boolean checkLogin(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Object obj = session.getAttribute(Const.SYSTEM_USER_SESSION);
//
//        return true;
//    }

    public String checkLogin(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        if(cookies == null ){
            return null;
        }
        if(CollectionUtils.isEmpty(Arrays.asList(cookies))){
            return null;
        }
        for (Cookie item : cookies) {
            if (Const.COOKIE_USER_NAME.equals(item.getName())) {
                sessionId = item.getValue();
                break;
            }
        }
        if(StringUtils.isNotBlank(sessionId)){
            String key = Const.SESSION_KEY_HEAD + sessionId;
            String userId = redisService.get(key);
            if(StringUtils.isBlank(userId)){
                return null;
            }

            return userId;
        }
        return null;


    }

}

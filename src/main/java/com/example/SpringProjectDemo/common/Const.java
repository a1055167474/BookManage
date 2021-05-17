package com.example.SpringProjectDemo.common;

/**
 * @author qinzhibin
 * @description 常量类
 * @date 2021/3/26
 */
public class Const {

    private static final String APPLICATION_PROPERTIES = "application.properties";

    private static final String ACTIVE_OPTION = System.getProperty("spring.profiles.active");

    public static final String RESPONSE_SUCCESS = "success";

    public static final String RESPONSE_FAILED = "failed";

    public static final String SYSTEM_USER_SESSION = "userInfo";

    public static final String SYSTEM_USER = "SystemUser";

    public static final String COOKIE_USER_NAME = "JSESSIONID";

    public static final String COOKIE_OUT_TIME = OptProperties
            .getPropertyValue(ACTIVE_OPTION, APPLICATION_PROPERTIES, "cookie.out.time");

    public static final String SESSION_OUT_TIME = OptProperties
            .getPropertyValue(ACTIVE_OPTION, APPLICATION_PROPERTIES, "session.out.time");

    public static final String SESSION_KEY_HEAD = OptProperties
            .getPropertyValue(ACTIVE_OPTION, APPLICATION_PROPERTIES,"sessionKeyHead");




}

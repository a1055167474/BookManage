package com.example.SpringProjectDemo.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author qinzhibin
 * @description 读取格式为.properties的文件内容
 * @date 2021/4/29
 */
public class OptProperties {

    private static Logger logger = LoggerFactory.getLogger(OptProperties.class);

    static Properties ctx = new Properties();

    private OptProperties(){}

    /**
     * @Description: 读取各个环境下的配置文件的属性值
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/4/29
     */
    public static String getPropertyValue(String env, String propFile, String key){
        String value = "";
        try{
            ClassLoader classLoader = OptProperties.class.getClassLoader();
            ctx = new Properties();
            String realName = propFile.replace(".", "-" + env + ".");
            ctx.load(classLoader.getResourceAsStream(realName));
            value = ctx.getProperty(key);
        }catch (Exception e){
            logger.info("获取配置文件参数异常：" + e);
        }
        return value;
    }

}

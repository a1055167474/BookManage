package com.example.SpringProjectDemo.common;

import java.util.Properties;

/**
 * @author chenzihan
 * @description 读取格式为.properties的文件内容
 * @date 2021/4/29
 */
public class OptProperties {

    static Properties ctx = new Properties();

    private OptProperties(){}

    /**
     * @Description: 读取各个环境下的配置文件的属性值
     * @Param:
     * @Author: chenzihan
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

        }
        return value;
    }

}

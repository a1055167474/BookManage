package com.example.SpringProjectDemo.common;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author qinzhibin
 * @description 公共方法类
 * @date 2021/5/24
 */
public class CommonUtils {

    private CommonUtils(){
        super();
    }


    /**
     *
     * @param property
     * @return 对象属性转换成字段 例如 userName to user_name
     */
    public static String propertyToField(String property){
        if(null == property) {
            return "";
        }
        char[] chars = property.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(CharUtils.isAsciiAlphaUpper(c)){
                sb.append("-").append(StringUtils.upperCase(CharUtils.toString(c)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


}

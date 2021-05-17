package com.example.SpringProjectDemo.utils;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.exception.BaseException;

/**
 * @author qinzhibin
 * @description 统一封装返回值
 * @date 2021/3/26
 */
public class ResultUtils {


    /**
     * @Description: 返回错误信息
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    public static <T> Response<T> ResultErrorUtil(BaseException e) {
        Response<T> result = new Response<>();
        result.setStatus(e.getErrorCode());
        result.setMessage(e.getErrorMessage());
        return result;
    }

    /**
     * @Description: 返回成功对象
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    public static <T> Response<T> ResultSuccessUtil(T entity) {
        Response<T> result = new Response<>();
        result.setStatus(Const.RESPONSE_SUCCESS);
        result.setResult(entity);
        return result;
    }

    /**
     * @Description: 返回成功对象、信息
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    public static <T> Response<T> ResultSuccessUtilMessage(T entity, String message) {
        Response<T> result = new Response<>();
        result.setStatus(Const.RESPONSE_SUCCESS);
        result.setResult(entity);
        result.setMessage(message);
        return result;
    }

    /**
     * @Description: 返回成功对象、信息、数量
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    public static <T> Response<T> ResultSuccessUtilMessage(T entity, String message, int total) {
        Response<T> result = new Response<>();
        result.setStatus(Const.RESPONSE_SUCCESS);
        result.setResult(entity);
        result.setMessage(message);
        result.setTotal(total);
        return result;
    }

    /**
     * @Description: 返回失败信息
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    public static <T> Response<T> ResultErrorUtil(String message) {
        Response<T> result = new Response<>();
        result.setStatus(Const.RESPONSE_FAILED);
        result.setMessage(message);
        return result;
    }

    /**
     * @Description: 返回失败对象、信息
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/26
     */
    public static <T> Response<T> ResultErrorUtilObject(T entity, String message) {
        Response<T> result = new Response<>();
        result.setStatus(Const.RESPONSE_SUCCESS);
        result.setResult(entity);
        result.setMessage(message);
        return result;
    }

}

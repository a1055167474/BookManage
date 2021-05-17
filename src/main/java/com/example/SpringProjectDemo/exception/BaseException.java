package com.example.SpringProjectDemo.exception;

/**
 * @author qinzhibin
 * @description 基础异常类
 * @date 2021/3/26
 */
public class BaseException extends Exception{

    private static final long serialVersionUID = 7685446091870590271L;

    private String errorCode = "";
    private String errorMessage = "";

    public BaseException(String message){
        super(message);
    }

    public BaseException(){

    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

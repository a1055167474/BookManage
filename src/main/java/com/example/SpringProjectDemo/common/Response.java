package com.example.SpringProjectDemo.common;

/**
 * @author qinzhibin
 * @description 接口响应类
 * @date 2021/3/25
 */
public class Response<T> {

    private String status;

    private String message;

    private T result;

    private  int total;

    public Response(){

        super();
    }

    public Response(String status, String message, T result, int total) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.total = total;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

package com.example.SpringProjectDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.service.*;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenzihan
 * @description 图书管理系统首页接口
 * @date 2021/4/1
 */
@RestController
@RequestMapping("/home")
public class HomePageController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private BookService bookService;

    @Resource
    private BorrowReturnService borrowReturnService;

    @Resource
    private LoginService loginService;

    @Resource
    private SessionService sessionService;

    /**
     * 近一周每日登陆人数
     *
     */
    @GetMapping("/getWeekUserLogin")
    public Response<?> getWeekUserLogin() {

        try{
            JSONObject json = userService.getWeekUserLogin();

            return ResultUtils.ResultSuccessUtilMessage(json,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }

    /**
     * 获取当前在线人数
     *
     */
    @GetMapping("/getLoginCount")
    public Response<?> getLoginCount() {

        try{
            JSONObject json = userService.getLoginCount();

            return ResultUtils.ResultSuccessUtilMessage(json,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }

    /**
     * 查询图书借阅量前五排行榜
     *
     */
    @GetMapping("/getBookTopFive")
    public Response<?> getBookTopFive() {

        try{
            JSONObject json = bookService.getBookTopFive();

            return ResultUtils.ResultSuccessUtilMessage(json,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }

    /**
     * 查询图书总量和种数
     *
     */
    @GetMapping("/getBookCount")
    public Response<?> getBookCount() {

        try{
            JSONObject json = bookService.getBookCount();

            return ResultUtils.ResultSuccessUtilMessage(json,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }

    /**
     * 查询用户总数、普通用户、管理员数量
     *
     */
    @GetMapping("/getUserCount")
    public Response<?> getUserCount() {

        try{
            JSONObject json = userService.getUserCount();

            return ResultUtils.ResultSuccessUtilMessage(json,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }

}

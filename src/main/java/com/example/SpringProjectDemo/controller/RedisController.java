package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.service.RedisService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author qinzhibin
 * @description Redis控制类
 * @date 2021/3/25
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    /**
     * @Description: 向redis中存储string类型的value 应用：向redis存储用户登录的sessionId，并设置有效期时长为30min
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/25
     */
    @GetMapping("/setRedis")
    @ApiOperation(value = "/setRedis",notes = "向redis中存储'name'的值", httpMethod = "GET", produces = "application/json", consumes = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message="操作成功", response = Response.class), @ApiResponse(code = 500, message = "操作失败", response = Response.class)})
    public Boolean setRedis(String name) {
        return redisService.set("name", name);
    }


    /**
     * @Description: 从redis中取出指定key的value
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/25
     */
    @GetMapping("/getRedis")
    @ApiOperation(value = "/getRedis",notes = "从redis中读取'name'的值", httpMethod = "GET", produces = "application/json", consumes = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message="操作成功", response = Response.class), @ApiResponse(code = 500, message = "操作失败", response = Response.class)})
    public String getRedis(@RequestParam String key) {

        String value = redisService.get(key);
        return value;

    }

}

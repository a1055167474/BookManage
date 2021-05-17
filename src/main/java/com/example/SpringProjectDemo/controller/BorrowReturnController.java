package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.service.BorrowReturnService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BorrowReturn)表控制层
 *
 * @author makejava
 * @since 2021-03-17 15:25:35
 */
@RestController
@RequestMapping("/borrowReturn")
public class BorrowReturnController {
    /**
     * 服务对象
     */
    @Resource
    private BorrowReturnService borrowReturnService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public BorrowReturn selectOne(Long id) {
        return this.borrowReturnService.queryById(id);
    }

}
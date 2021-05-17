package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.entity.Book;
import com.example.SpringProjectDemo.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Book)表控制层
 *
 * @author makejava
 * @since 2021-03-17 15:24:42
 */
@RestController
@RequestMapping("/book")
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Book selectOne(Long id) {
        return this.bookService.queryById(id);
    }

}
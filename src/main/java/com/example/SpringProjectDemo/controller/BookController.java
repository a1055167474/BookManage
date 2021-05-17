package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.Book;
import com.example.SpringProjectDemo.service.BookService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 查询所有图书信息
     *
     */
    @PostMapping("/selectAllBook")
    public Response<List<Book>> selectAllBook(@RequestBody Book book) {

        try{

            List<Book> bookList =  bookService.selectAllBook(book);
            return ResultUtils.ResultSuccessUtilMessage(bookList,"查询图书信息成功",bookList.size());

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询图书信息异常");
    }

    /**
     * 根据id删除图书信息
     *
     */
    @GetMapping("/deleteBookById")
    public Response<?> deleteBookById(@RequestParam long id) {

        try{

            boolean i  =  bookService.deleteById(id);
            if(i){
                return ResultUtils.ResultSuccessUtilMessage(null,"删除图书信息成功");
            }
            return ResultUtils.ResultErrorUtil("删除图书信息失败");

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("删除图书信息异常");
    }

    /**
     * 新增图书信息
     *
     */
    @PostMapping("/insertBook")
    public Response<?> insertBook(@RequestBody Book book) {

        try{
            Book book1  =  bookService.insert(book);

            return ResultUtils.ResultSuccessUtilMessage(book1,"新增图书信息成功");

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("新增图书信息异常");
    }



    /**
     * 保存图书信息
     *
     */
    @PostMapping("/updateBook")
    public Response<?> updateBook(@RequestBody Book book) {

        try{
            Book book1  =  bookService.update(book);

            return ResultUtils.ResultSuccessUtilMessage(book1,"保存图书信息成功");

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("保存图书信息异常");
    }

}
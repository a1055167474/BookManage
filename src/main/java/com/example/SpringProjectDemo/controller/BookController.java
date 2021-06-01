package com.example.SpringProjectDemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.Book;
import com.example.SpringProjectDemo.entity.Page;
import com.example.SpringProjectDemo.service.BookService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
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
    public Response<List<Book>> selectAllBook(@RequestBody JSONObject jsonObject) {

        try{

            Object bookObject = jsonObject.get("book");
            JSONObject bookJson = JSONObject.parseObject(JSON.toJSONString(bookObject));
            Book book = bookJson.toJavaObject(Book.class);

            Object pageObject = jsonObject.get("page");
            JSONObject pageJson = JSONObject.parseObject(JSON.toJSONString(pageObject));
            Page page = pageJson.toJavaObject(Page.class);

            //例如前端传递的是2021-05-19，页面上显示的是2021-05-19，但实际上数据库中存储的可能是2021-05-19 16:00:00
            //需要对endTime天数加一
            if(book.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(book.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);
                book.setEndTime(c.getTime());
            }

            //分页查询图书列表
            List<Book> bookList =  bookService.selectAllBook(book, page);

            //查询图书总数量
            int count = bookService.selectAllBookCount(book);
            return ResultUtils.ResultSuccessUtilMessage(bookList,"查询图书信息成功",count);

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
            if(StringUtils.isBlank(book.getName())){
                return ResultUtils.ResultErrorUtil("未获取书籍名称");
            }
            if(StringUtils.isBlank(book.getAuthor())){
                return ResultUtils.ResultErrorUtil("未获取到书籍");
            }
            if(book.getAmount() == null){
                return ResultUtils.ResultErrorUtil("未获取到书籍数量");
            }
            if(book.getState() == null){
                return ResultUtils.ResultErrorUtil("未获取到书籍状态");
            }
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
            if(StringUtils.isBlank(book.getName())){
                return ResultUtils.ResultErrorUtil("未获取书籍名称");
            }
            if(StringUtils.isBlank(book.getAuthor())){
                return ResultUtils.ResultErrorUtil("未获取到书籍");
            }
            if(book.getAmount() == null){
                return ResultUtils.ResultErrorUtil("未获取到书籍数量");
            }
            if(book.getState() == null){
                return ResultUtils.ResultErrorUtil("未获取到书籍状态");
            }
            Book book1  =  bookService.update(book);

            return ResultUtils.ResultSuccessUtilMessage(book1,"保存图书信息成功");

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("保存图书信息异常");
    }

}
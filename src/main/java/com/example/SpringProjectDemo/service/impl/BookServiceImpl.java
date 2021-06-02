package com.example.SpringProjectDemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.dao.BorrowReturnDao;
import com.example.SpringProjectDemo.entity.Book;
import com.example.SpringProjectDemo.dao.BookDao;
import com.example.SpringProjectDemo.entity.Page;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.entity.vo.BorrowReturnVo;
import com.example.SpringProjectDemo.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Book)表服务实现类
 *
 * @author makejava
 * @since 2021-03-17 15:24:42
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    @Resource
    private BorrowReturnDao borrowReturnDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Book queryById(Long id) {
        return this.bookDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Book> queryAllByLimit(int offset, int limit) {
        return this.bookDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book insert(Book book) {
        book.setCreateTime(new Date());
        book.setIsDeleted(0);
        bookDao.insert(book);
        return book;
    }

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book update(Book book) {
        this.bookDao.update(book);
        return this.queryById(book.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.bookDao.deleteById(id) > 0;
    }

    @Override
    public List<Book> selectAllBook(Book book, Page page) {
        return bookDao.queryAll(book, page);
    }

    @Override
    public int selectAllBookCount(Book book){
        return bookDao.queryAllCount(book);
    }

    /**
     * 查询图书排行前5的图书信息
     * @return
     */
    @Override
    public JSONObject getBookTopFive() {
        JSONObject j = new JSONObject();
        List<BorrowReturnVo> borrowReturnVoList = borrowReturnDao.getBookTopFive();

        List<String> book = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        //将日期和数量分开成两个列表以匹配前端组件
        for(BorrowReturnVo br : borrowReturnVoList){
            book.add(br.getBookName());
            count.add(br.getCount());
        }
        j.put("book",book);
        j.put("count",count);
        return j;
    }


    /**
     * 查询图书种数和总量
     * @return
     */
    @Override
    public JSONObject getBookCount() {
        JSONObject j = new JSONObject();
        List<Book> bookList = bookDao.selectAll();
        int sum = 0;
        for(Book b : bookList){
            sum += b.getAmount();
        }
        j.put("bookType",bookList.size());
        j.put("allCount",sum);
        return j;
    }


}
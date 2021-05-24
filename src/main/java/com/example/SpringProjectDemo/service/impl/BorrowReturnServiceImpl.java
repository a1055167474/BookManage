package com.example.SpringProjectDemo.service.impl;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.dao.BookDao;
import com.example.SpringProjectDemo.entity.Book;
import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.dao.BorrowReturnDao;
import com.example.SpringProjectDemo.entity.vo.BorrowReturnVo;
import com.example.SpringProjectDemo.service.BorrowReturnService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (BorrowReturn)表服务实现类
 *
 * @author makejava
 * @since 2021-03-17 15:25:35
 */
@Service("borrowReturnService")
public class BorrowReturnServiceImpl implements BorrowReturnService {
    @Resource
    private BorrowReturnDao borrowReturnDao;

    @Resource
    private BookDao bookDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BorrowReturn queryById(Long id) {
        return this.borrowReturnDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param borrowReturn 查询起始位置
     * @return 对象列表
     */
    @Override
    public List<BorrowReturn> queryAllByLimit(BorrowReturnVo borrowReturn) {
        if(borrowReturn.getPage() < 0){
            borrowReturn.setPage(0);
        }
        borrowReturn.setStart((borrowReturn.getPage() - 1) * borrowReturn.getSize());
        return this.borrowReturnDao.queryAll(borrowReturn);
    }

    @Override
    public int queryTotal(BorrowReturnVo borrowReturn) {
        return this.borrowReturnDao.queryTotal(borrowReturn);
    }

    /**
     * 新增书籍借出记录
     *
     * @param borrowReturn 实例对象
     * @return 实例对象
     */
    @Override
    public Response<?> insert(BorrowReturn borrowReturn) {

        //判断当前用户是否有图书借用记录
        Long userId = borrowReturn.getUserId();
        BorrowReturn br = borrowReturnDao.selectByUserId(userId);
        if(br != null){
            return ResultUtils.ResultErrorUtil("当前用户存在未归还图书，无法继续借用，请归还后重试");
        }
        //判断当前图书库存是否满足借用条件
        Book book = bookDao.queryById(borrowReturn.getBookId());
        Integer amount = book.getAmount();
        if(amount < 1){
            return ResultUtils.ResultErrorUtil("当前图书库存不足，无法借阅");
        }
        //可以借用，减去库存并且入库
        book.setAmount(amount - borrowReturn.getAmount());
        bookDao.update(book);

        //图书借出记录入库
        borrowReturn.setCreateTime(new Date());
        borrowReturn.setState(0);
        this.borrowReturnDao.insert(borrowReturn);
        return ResultUtils.ResultSuccessUtilMessage(null,"书籍借阅成功");
    }

    /**
     * 借阅书籍归还
     *
     * @param borrowReturn 实例对象
     * @return 实例对象
     */
    @Override
    public BorrowReturn update(BorrowReturn borrowReturn) {

        //图书归还
        borrowReturn.setReturnTime(new Date());
        borrowReturn.setState(1);
        this.borrowReturnDao.update(borrowReturn);

        //书籍库存数量增加
        Book book = bookDao.queryById(borrowReturn.getBookId());
        book.setAmount(book.getAmount() + borrowReturn.getAmount());
        bookDao.update(book);
        return this.queryById(borrowReturn.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.borrowReturnDao.deleteById(id) > 0;
    }
}
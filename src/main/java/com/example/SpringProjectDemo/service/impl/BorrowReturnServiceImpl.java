package com.example.SpringProjectDemo.service.impl;

import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.dao.BorrowReturnDao;
import com.example.SpringProjectDemo.service.BorrowReturnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BorrowReturn> queryAllByLimit(int offset, int limit) {
        return this.borrowReturnDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param borrowReturn 实例对象
     * @return 实例对象
     */
    @Override
    public BorrowReturn insert(BorrowReturn borrowReturn) {
        this.borrowReturnDao.insert(borrowReturn);
        return borrowReturn;
    }

    /**
     * 修改数据
     *
     * @param borrowReturn 实例对象
     * @return 实例对象
     */
    @Override
    public BorrowReturn update(BorrowReturn borrowReturn) {
        this.borrowReturnDao.update(borrowReturn);
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
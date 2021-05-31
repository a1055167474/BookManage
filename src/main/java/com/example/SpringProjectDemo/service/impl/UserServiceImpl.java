package com.example.SpringProjectDemo.service.impl;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.dao.BorrowReturnDao;
import com.example.SpringProjectDemo.dao.UserDao;
import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.entity.vo.UserVo;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-03-17 15:26:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private BorrowReturnDao borrowReturnDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Response<?> insert(User user) {

        //判断用户的账号是否存在
        User user1 = userDao.getUserByAccount(user.getAccount());
        if(user1 != null){
            return ResultUtils.ResultErrorUtil("此账号已存在！");
        }
        user.setIsDeleted(0);
        user.setState(0);
        user.setCreateTime(new Date());
        this.userDao.insert(user);
        return ResultUtils.ResultSuccessUtilMessage(null, "新增用户信息成功");
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Response<?> update(User user) {

        //判断用户的账号是否存在
        User user1 = userDao.getUserByAccount(user.getAccount());
        if(user1 != null){
            if(!user1.getId().equals(user.getId())) {
                return ResultUtils.ResultErrorUtil("此账号已存在！");
            }
        }
        this.userDao.update(user);
        return ResultUtils.ResultSuccessUtilMessage(null, "用户信息更新成功");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }

    /**
     * @Description: 根据条件查询用户信息
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/5/17
     */
    @Override
    public List<User> selectAllUser(UserVo user) {
        user.setStart((user.getPage() - 1) * user.getSize());
        return userDao.queryAll(user);
    }

    @Override
    public User selectByUserId(Long userId) {
        return userDao.selectByUserId(userId);
    }

    @Override
    public int selectUserCount(UserVo user) {
        user.setStart((user.getPage() - 1) * user.getSize());
        return userDao.queryCount(user);
    }

    @Override
    public Response<?> deleteUser(User user) {
        //判断当前用户下是否有图书借出记录或者挂失记录
        List<BorrowReturn> brList = borrowReturnDao.selectByUserId2(user.getId());

        if(!CollectionUtils.isEmpty(brList)){
            return ResultUtils.ResultErrorUtil("当前用户下存在未处理的借阅记录，无法删除");
        }
        user.setIsDeleted(1);
        userDao.update(user);
        return  ResultUtils.ResultSuccessUtilMessage(null,"删除成功");
    }


}
package com.example.SpringProjectDemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.dao.BorrowReturnDao;
import com.example.SpringProjectDemo.dao.SessionDao;
import com.example.SpringProjectDemo.dao.UserDao;
import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.entity.Session;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.entity.vo.SessionVo;
import com.example.SpringProjectDemo.entity.vo.UserVo;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Resource
    private SessionDao sessionDao;

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
     * @Author: chenzihan
     * @Date: 2021/3/17
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

    /**
     * 近一周每日登陆人数
     *
     */
    @Override
    public JSONObject getWeekUserLogin() {
        JSONObject j = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        //获取近一周的每日登录人数，包含今天
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 6);
        List<SessionVo> sessionList = sessionDao.selectWeekDate();

        List<String> weekDate = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        //将日期和数量分开成两个列表以匹配前端组件
        for(SessionVo s : sessionList){
            weekDate.add(sdf.format(s.getWeekDate()));
            count.add(s.getCount());
        }
        j.put("weekDate",weekDate);
        j.put("count",count);
        return j;
    }

    @Override
    public JSONObject getLoginCount() {

        JSONObject j = new JSONObject();
        List<Session> sessionList = sessionDao.selectAllInvalid();
        List<Long> ids = new ArrayList<>();
        for(Session s : sessionList){
            Long d = s.getCreateTime().getTime();
            Long d2 = new Date().getTime();
            //判断session信息是否超出规定时间
            int diffSeconds = (int) ((d2 - d) / 1000);
            if(diffSeconds <= Const.SESSION_OUT_TIME) {
                ids.add(s.getId());
            }
        }
        j.put("count",ids.size());
        return j;
    }

    /**
     * 查询用户总数、普通用户、管理员数量
     *
     */
    @Override
    public JSONObject getUserCount() {
        JSONObject j = new JSONObject();
        int allCount = 0;  //用户总数
        int user = 0;  //普通用户
        int manager = 0;  //管理员
        List<User> userList = userDao.selectAll();
        allCount = userList.size();
        for(User u : userList){
            if(u.getUserRole() == 0){
                user++;
            }
            if(u.getUserRole() == 1){
                manager++;
            }
        }
        j.put("allCount",allCount);
        j.put("user",user);
        j.put("manager",manager);
        return j;
    }


}
package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.entity.vo.BorrowReturnVo;
import com.example.SpringProjectDemo.service.BorrowReturnService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * (BorrowReturn)表控制层
 *
 * @author makejava
 * @since 2021-03-17 15:25:35
 */
@RestController
@RequestMapping("/borrowReturn")
public class BorrowReturnController extends BaseController{
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


    /**
     * 新增借出记录
     *
     */
    @PostMapping("/addBorrowBook")
    public Response<?> addBorrowBook(@RequestBody BorrowReturn borrowReturn, HttpServletRequest request) {

        try{
            if(borrowReturn.getBookId() == null){
                return ResultUtils.ResultErrorUtil("未获取到书籍id");
            }
            if(borrowReturn.getAmount() == null){
                return ResultUtils.ResultErrorUtil("未获取到书籍数量");
            }
            User user = getCurrentUser(request);
            if(user == null){
                return ResultUtils.ResultErrorUtil("未获取到当前登录人");
            }
            borrowReturn.setUserId(user.getId());
            return borrowReturnService.insert(borrowReturn);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("新增借出记录异常");
    }

    /**
     * 归还图书
     *
     */
    @PostMapping("/returnBook")
    public Response<?> returnBook(@RequestBody BorrowReturn borrowReturn, HttpServletRequest request) {

        try{
            if(borrowReturn.getId() == null){
                return ResultUtils.ResultErrorUtil("未获取到借用记录id");
            }
            User user = getCurrentUser(request);
            if(user == null){
                return ResultUtils.ResultErrorUtil("未获取到当前登录信息");
            }
            return borrowReturnService.update(borrowReturn,user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("书籍归还异常");
    }

    /**
     * 获取图书借用记录
     *
     */
    @GetMapping("/getBorrowList")
    public Response<?> getBorrowList(BorrowReturnVo borrowReturn) {

        try{

            //例如前端传递的是2021-05-19，页面上显示的是2021-05-19，但实际上数据库中存储的可能是2021-05-19 16:00:00
            //需要对endTime天数加一
            if(borrowReturn.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(borrowReturn.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);
                borrowReturn.setEndTime(c.getTime());
            }
            if(borrowReturn.getReturnEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(borrowReturn.getReturnEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);
                borrowReturn.setReturnEndTime(c.getTime());
            }

            //查询图书借用数据
            List<BorrowReturnVo> brList = borrowReturnService.queryAllByLimit(borrowReturn);

            //查询借用记录总条数
            Integer count = borrowReturnService.queryTotal(borrowReturn);
            return ResultUtils.ResultSuccessUtilMessage(brList,"查询成功",count);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }


    /**
     * 获取当前登录人的图书借用记录
     *
     */
    @GetMapping("/getCurrentUserBorrowList")
    public Response<?> getCurrentUserBorrowList(BorrowReturnVo borrowReturn, HttpServletRequest request) {

        try{
            User user = getCurrentUser(request);
            if(user == null){
                return ResultUtils.ResultErrorUtil("未获取当前登录信息");
            }
            borrowReturn.setUserId(user.getId());
            List<BorrowReturnVo> brList = borrowReturnService.queryAllByLimit(borrowReturn);

            //查询借用记录总条数
            Integer count = borrowReturnService.queryTotal(borrowReturn);
            return ResultUtils.ResultSuccessUtilMessage(brList,"查询成功",count);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }


    /**
     * 图书挂失
     *
     */
    @GetMapping("/lostReport")
    public Response<?> lostReport(BorrowReturnVo borrowReturn, HttpServletRequest request) {

        try{
            if(borrowReturn.getId() == null){
                return ResultUtils.ResultErrorUtil("未获取到借用记录Id");
            }
            User user = getCurrentUser(request);
            if(user == null){
                return ResultUtils.ResultErrorUtil("未获取当前登录信息");
            }
            return borrowReturnService.lostReport(borrowReturn,user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }


    /**
     * 管理员处理图书挂失记录
     *
     */
    @GetMapping("/handleLostReport")
    public Response<?> handleLostReport(BorrowReturnVo borrowReturn, HttpServletRequest request) {

        try{
            if(borrowReturn.getId() == null){
                return ResultUtils.ResultErrorUtil("未获取到借用记录Id");
            }
            User user = getCurrentUser(request);
            if(user == null){
                return ResultUtils.ResultErrorUtil("未获取当前登录信息");
            }
            return borrowReturnService.handleLostReport(borrowReturn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("处理异常");
    }

    /**
     * 查询图书挂失记录
     *
     */
    @GetMapping("/getLostReportList")
    public Response<?> getLostReportList(BorrowReturnVo borrowReturn, HttpServletRequest request) {

        try{
            User user = getCurrentUser(request);
            if(user == null){
                return ResultUtils.ResultErrorUtil("未获取当前登录信息");
            }
            boolean i = isManager(user);
            if(!i){
                return ResultUtils.ResultErrorUtil("没有权限");
            }

            //例如前端传递的是2021-05-19，页面上显示的是2021-05-19，但实际上数据库中存储的可能是2021-05-19 16:00:00
            //需要对endTime天数加一
            if(borrowReturn.getEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(borrowReturn.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);
                borrowReturn.setEndTime(c.getTime());
            }
            if(borrowReturn.getReturnEndTime() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(borrowReturn.getReturnEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);
                borrowReturn.setReturnEndTime(c.getTime());
            }
            //查询图书挂失数据
            List<BorrowReturnVo> brList = borrowReturnService.getLostReportList(borrowReturn);
            //查询挂失记录总条数
            int count = borrowReturnService.getLostReportTotal(borrowReturn);
            return ResultUtils.ResultSuccessUtilMessage(brList,"查询成功",count);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("查询异常");
    }


}
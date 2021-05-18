package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.BorrowReturn;
import com.example.SpringProjectDemo.service.BorrowReturnService;
import com.example.SpringProjectDemo.utils.ResultUtils;
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


    /**
     * 新增借出记录
     *
     */
    @PostMapping("/addBorrowBook")
    public Response<BorrowReturn> addBorrowBook(@RequestBody BorrowReturn borrowReturn) {

        try{

            BorrowReturn result = borrowReturnService.insert(borrowReturn);
            return ResultUtils.ResultSuccessUtilMessage(result,"新增借出记录成功");

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtils.ResultErrorUtil("新增借出记录异常");
    }

}
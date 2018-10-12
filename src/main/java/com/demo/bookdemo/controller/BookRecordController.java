package com.demo.bookdemo.controller;

import com.demo.bookdemo.entity.Book;
import com.demo.bookdemo.entity.BookRecord;
import com.demo.bookdemo.service.IBookRecordService;
import com.demo.bookdemo.service.IBookService;
import com.demo.bookdemo.util.BusinessException;
import com.demo.bookdemo.util.PageResult;
import com.demo.bookdemo.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类 名: BookRecordController.java
 * 描 述: 租书记录Controller
 * 作 者: 张振国
 * 创 建：2018/10/12 22:19
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
@Controller
@RequestMapping("/book/record")
public class BookRecordController {

    @Resource(name = "bookServiceImpl")
    private IBookService bookService;

    @Resource(name = "bookRecordServiceImpl")
    private IBookRecordService bookRecordService;

    /**
     * 描 述： 跳转到借书页面
     * 作 者： 张振国
     * 历 史： (版本) 作者 时间 注释
     * @param bookId 图书ID
     * @return 页面url
     */
    @RequestMapping("/lend")
    public String lend(Long bookId, Model model){
        //查询要借的图书信息
        Book book = bookService.getById(bookId);
        model.addAttribute("book",book);
        return "lend";
    }

    /**
     * 描 述： 提交借书操作
     * 作 者： 张振国
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 借书信息
     * @return 结果
     */
    @RequestMapping(value = "/doLend",method = RequestMethod.POST)
    @ResponseBody
    public Result doLend(@RequestBody BookRecord bookRecord){
        //调用业务层新增图书记录信息
        try {
            bookRecordService.add(bookRecord);
        }catch (BusinessException e){
            return Result.newFailure(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return Result.newFailure("服务器异常！");
        }
        return Result.newSuccess();
    }

    /**
     * 描 述： 跳转到租书记录列表页
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @return 首页url
     */
    @RequestMapping("")
    public String index(){
        return "book_record";
    }

    /**
     * 描 述： 查询图书记录列表数据
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 查询条件
     * @return 图书列表数据
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageResult<BookRecord> list(BookRecord bookRecord){

        //实例化分页辅助类
        PageResult<BookRecord> pageResult = new PageResult<>();
        //设置分页数据
        pageResult.setPageIndex(bookRecord.getPageIndex());
        pageResult.setPageSize(bookRecord.getPageSize());
        //调用业务层查询方法
        bookRecordService.findList(bookRecord,pageResult);
        //设置结果状态
        pageResult.setCode("SUCCESS");
        //将数据返回到前端
        return pageResult;
    }

    /**
     * 描 述： 还书
     * 作 者： 张振国
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 租书信息
     * @return 结果
     */
    @RequestMapping("/revert")
    @ResponseBody
    public Result<Double> revert(@RequestBody BookRecord bookRecord){
        bookRecord = bookRecordService.getById(bookRecord.getId());
        //查询图书信息
        Book book = bookService.getById(bookRecord.getBookId());
        bookRecord.setEndDate(new Date());
        Map<String, Long> datePoor = getDatePoor(bookRecord.getStartDate(), bookRecord.getEndDate());
        long between = (datePoor.get("day") * 24 * 60) + (datePoor.get("hour") * 60) + (datePoor.get("min"));
        //计算价格
        bookRecord.setTotalPrice(between * book.getPrice());
        //调用业务层修改信息
        bookRecordService.update(bookRecord);
        return Result.newSuccess(bookRecord.getTotalPrice());
    }

    public Map<String,Long> getDatePoor(Date startDate, Date endDate) {

        Map<String,Long> map = new HashMap<>();
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        Long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        map.put("day",day);
        map.put("hour",hour);
        map.put("min",min);
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return map;
    }
}

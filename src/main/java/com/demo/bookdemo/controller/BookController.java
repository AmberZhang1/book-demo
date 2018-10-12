package com.demo.bookdemo.controller;

import com.demo.bookdemo.entity.Book;
import com.demo.bookdemo.service.IBookService;
import com.demo.bookdemo.util.PageResult;
import com.demo.bookdemo.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类 名: BookController.java
 * 描 述: 图书Controller
 * 作 者: lily
 * 创 建：2018/10/12 15:02
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Resource(name = "bookServiceImpl")
    private IBookService bookService;

    /**
     * 描 述： 跳转到首页
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @return 首页url
     */
    @RequestMapping("")
    public String index(){
        return "index";
    }

    /**
     * 描 述： 查询图书列表数据
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 查询条件
     * @return 图书列表数据
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageResult<Book> list(Book book){

        //实例化分页辅助类
        PageResult<Book> pageResult = new PageResult<>();
        //设置分页数据
        pageResult.setPageIndex(book.getPageIndex());
        pageResult.setPageSize(book.getPageSize());
        //调用业务层查询方法
        bookService.findList(book,pageResult);
        //设置结果状态
        pageResult.setCode("SUCCESS");
        //将数据返回到前端
        return pageResult;
    }
    
    /**
     * 描 述： 跳转编辑页面
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param id 图书id
     * @return 页面url
     */
    @RequestMapping("/edit")
    public String edit(Long id, Model model){
        //判断是新增还是修改
        if (id != null){
            //如果是修改则查询原数据展现到页面上
            Book book = bookService.getById(id);
            model.addAttribute("book",book);
        }
        return "edit";
    }

    /**
     * 描 述： 保存图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 图书
     * @return 保存结果
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody Book book){

        try {
            //判断是新增还是修改
            if (book.getId() != null){
                //修改
                bookService.update(book);
            }else {
                //新增
                //调用业务层方法新增图书
                bookService.add(book);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.newFailure("服务器异常！");
        }
        return Result.newSuccess();
    }

    /**
     * 描 述： 批量删除图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param ids ids
     * @return 删除结果
     */
    @RequestMapping("/del")
    @ResponseBody
    public Result delete(@RequestBody String ids){
        try {
            //将String转为集合
            String[] idArray = ids.split(",");
            List<Long> list = new ArrayList<>();
            for (String s : idArray) {
                list.add(Long.valueOf(s));
            }
            //调用业务层方法删除
            bookService.del(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.newFailure("服务器异常！");
        }
        return Result.newSuccess();
    }

















}

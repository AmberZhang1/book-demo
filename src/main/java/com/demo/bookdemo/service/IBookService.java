package com.demo.bookdemo.service;

import com.demo.bookdemo.entity.Book;
import com.demo.bookdemo.util.PageResult;

import java.util.List;

/**
 * 类 名: IBookService.java
 * 描 述: 图书业务接口
 * 作 者: lily
 * 创 建：2018/10/12 14:57
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
public interface IBookService {

    /**
     * 描 述： 查询图书集合
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 查询条件
     */
    void findList(Book book, PageResult<Book> page);

    /**
     * 描 述： 根据ID查询图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param id 图书id
     * @return 图书对象
     */
    Book getById(Long id);

    /**
     * 描 述： 新增图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 图书对象
     */
    void add(Book book);

    /**
     * 描 述： 修改图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 图书对象
     */
    void update(Book book);

    /**
     * 描 述： 根据批量删除图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param ids 图书id集合
     */
    void del(List<Long> ids);
}

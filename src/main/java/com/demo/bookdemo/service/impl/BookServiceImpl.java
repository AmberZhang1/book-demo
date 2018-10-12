package com.demo.bookdemo.service.impl;

import com.demo.bookdemo.dao.IBookMapper;
import com.demo.bookdemo.entity.Book;
import com.demo.bookdemo.service.IBookService;
import com.demo.bookdemo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类 名: BookServiceImpl.java
 * 描 述: 图书业务实现类
 * 作 者: lily
 * 创 建：2018/10/12 14:58
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
@Service("bookServiceImpl")
@Transactional
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookMapper bookMapper;

    /**
     * 描 述： 查询图书集合
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 查询条件
     */
    @Override
    public void findList(Book book, PageResult<Book> page) {
        //查询总条数
        int count = bookMapper.findCount(book);
        //设置总条数
        page.setRows(count);
        //查询图书集合
        List<Book> list = bookMapper.findList(book, (page.getPageIndex() - 1) * 10 , page.getPageSize());
        page.setResult(list);
    }

    /**
     * 描 述： 根据ID查询图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     *
     * @param id 图书id
     * @return 图书对象
     */
    @Override
    public Book getById(Long id) {
        return bookMapper.getById(id);
    }

    /**
     * 描 述： 新增图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     *
     * @param book 图书对象
     */
    @Override
    public void add(Book book) {
        bookMapper.add(book);
    }

    /**
     * 描 述： 修改图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     *
     * @param book 图书对象
     */
    @Override
    public void update(Book book) {
        bookMapper.update(book);
    }

    /**
     * 描 述： 根据批量删除图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     *
     * @param ids 图书id集合
     */
    @Override
    public void del(List<Long> ids) {
        bookMapper.del(ids);
    }
}

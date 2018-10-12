package com.demo.bookdemo.service.impl;

import com.demo.bookdemo.dao.IBookRecordMapper;
import com.demo.bookdemo.entity.Book;
import com.demo.bookdemo.entity.BookRecord;
import com.demo.bookdemo.service.IBookRecordService;
import com.demo.bookdemo.service.IBookService;
import com.demo.bookdemo.util.BusinessException;
import com.demo.bookdemo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 类 名: BookRecordServiceImpl.java
 * 描 述: 租书记录业务实现类
 * 作 者: 张振国
 * 创 建：2018/10/12 22:16
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
@Service("bookRecordServiceImpl")
@Transactional
public class BookRecordServiceImpl implements IBookRecordService {

    @Autowired
    private IBookRecordMapper bookRecordMapper;

    @Resource(name = "bookServiceImpl")
    private IBookService bookService;
    /**
     * 描 述： 查询图书记录集合
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     *
     * @param bookRecord 查询条件
     */
    @Override
    public void findList(BookRecord bookRecord, PageResult<BookRecord> page) {
        //查询总条数
        int count = bookRecordMapper.findCount(bookRecord);
        //设置总条数
        page.setRows(count);
        //查询图书集合
        List<BookRecord> list = bookRecordMapper.findList(bookRecord, (page.getPageIndex() - 1) * 10 , page.getPageSize());
        page.setResult(list);
    }

    /**
     * 描 述： 根据ID查询图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     *
     * @param id 图书id
     * @return 图书记录对象
     */
    @Override
    public BookRecord getById(Long id) {
        return bookRecordMapper.getById(id);
    }

    /**
     * 描 述： 新增图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     *
     * @param bookRecord 图书记录对象
     */
    @Override
    public void add(BookRecord bookRecord) throws BusinessException {
        //查出图书信息
        Book book = bookService.getById(bookRecord.getBookId());
        //判断当前剩余数量是否为0
        if (book.getCurrent() <= 0){
            throw new BusinessException("图书库存不足！");
        }
        //修改图书数量
        book.setCurrent(book.getCurrent() - 1);
        book.setAmount(book.getAmount() + 1);
        bookService.update(book);
        bookRecord.setStartDate(new Date());
        //新增图书记录表
        bookRecordMapper.add(bookRecord);
    }

    /**
     * 描 述： 修改图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     *
     * @param bookRecord 图书记录对象
     */
    @Override
    public void update(BookRecord bookRecord) {
        //查出图书信息
        Book book = bookService.getById(bookRecord.getBookId());
        //修改图书数量
        book.setCurrent(book.getCurrent() + 1);
        book.setAmount(book.getAmount() - 1);
        bookService.update(book);
        bookRecordMapper.update(bookRecord);
    }

}

package com.demo.bookdemo.service;

import com.demo.bookdemo.entity.BookRecord;
import com.demo.bookdemo.util.BusinessException;
import com.demo.bookdemo.util.PageResult;

import java.util.List;

/**
 * 类 名: IBookRecordService.java
 * 描 述: 租书记录业务接口
 * 作 者: 张振国
 * 创 建：2018/10/12 22:14
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
public interface IBookRecordService {

    /**
     * 描 述： 查询图书记录集合
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 查询条件
     */
    void findList(BookRecord bookRecord, PageResult<BookRecord> page);

    /**
     * 描 述： 根据ID查询图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param id 图书id
     * @return 图书记录对象
     */
    BookRecord getById(Long id);

    /**
     * 描 述： 新增图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 图书记录对象
     */
    void add(BookRecord bookRecord) throws BusinessException;

    /**
     * 描 述： 修改图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 图书记录对象
     */
    void update(BookRecord bookRecord);


}

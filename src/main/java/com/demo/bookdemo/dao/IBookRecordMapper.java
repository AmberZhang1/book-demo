package com.demo.bookdemo.dao;

import com.demo.bookdemo.entity.Book;
import com.demo.bookdemo.entity.BookRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类 名: IBookRecordMapper.java
 * 描 述: 租书记录数据接口
 * 作 者: 张振国
 * 创 建：2018/10/12 22:07
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
@Repository
public interface IBookRecordMapper {

    /**
     * 描 述： 查询图书集合
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 查询条件
     * @param pageIndex 当前页数
     * @param pageSize 页大小
     * @return 图书记录集合对象
     */
    List<BookRecord> findList(@Param("item") BookRecord bookRecord, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    /**
     * 描 述： 查询图书总条数
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 查询条件
     * @return 图书记录集合对象
     */
    int findCount(@Param("item") BookRecord bookRecord);

    /**
     * 描 述： 根据ID查询图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param id 图书id
     * @return 图书记录对象
     */
    BookRecord getById(@Param("id") Long id);

    /**
     * 描 述： 新增图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 图书记录对象
     */
    void add(@Param("item") BookRecord bookRecord);

    /**
     * 描 述： 修改图书记录
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param bookRecord 图书记录对象
     */
    void update(@Param("item") BookRecord bookRecord);

}

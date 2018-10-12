package com.demo.bookdemo.dao;

import com.demo.bookdemo.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类 名: BookMapper.java
 * 描 述: book数据查询接口
 * 作 者: lily
 * 创 建：2018/10/12 14:45
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
@Repository
public interface IBookMapper {

    /**
     * 描 述： 查询图书集合
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 查询条件
     * @param pageIndex 当前页数
     * @param pageSize 页大小
     * @return 图书集合对象
     */
    List<Book> findList(@Param("item") Book book, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    /**
     * 描 述： 查询图书总条数
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 查询条件
     * @return 图书集合对象
     */
    int findCount(@Param("item") Book book);

    /**
     * 描 述： 根据ID查询图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param id 图书id
     * @return 图书对象
     */
    Book getById(@Param("id") Long id);

    /**
     * 描 述： 新增图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 图书对象
     */
    void add(@Param("item") Book book);

    /**
     * 描 述： 修改图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param book 图书对象
     */
    void update(@Param("item") Book book);

    /**
     * 描 述： 根据批量删除图书
     * 作 者： lily
     * 历 史： (版本) 作者 时间 注释
     * @param ids 图书id集合
     */
    void del(@Param("ids") List<Long> ids);

}

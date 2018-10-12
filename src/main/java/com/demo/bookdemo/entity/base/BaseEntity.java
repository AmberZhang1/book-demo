package com.demo.bookdemo.entity.base;

import java.io.Serializable;

/**
 * 类 名: BaseEntity.java
 * 描 述: 实体类基类
 * 作 者: lily
 * 创 建：2018/10/12 15:49
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
public class BaseEntity implements Serializable {

    /** id */
    private Long id;

    /** 当前页数 */
    private Integer pageIndex = 1;

    /** 每页条数 */
    private Integer pageSize = 10;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

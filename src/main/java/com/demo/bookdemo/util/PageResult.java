package com.demo.bookdemo.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类 名: PageResult.java
 * 描 述: 分页辅助类
 * 作 者: lily
 * 创 建：2018/10/12 15:29
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
public class PageResult<T> implements Serializable {

    /** 当前页数据 */
    private List<T> result = new ArrayList<T>();

    /** 当前页数 */
    private Integer pageIndex = 1;

    /** 每页条数 */
    private Integer pageSize = 10;

    /** 总条数 */
    private Integer rows;

    /** 查询状态 */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
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

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}

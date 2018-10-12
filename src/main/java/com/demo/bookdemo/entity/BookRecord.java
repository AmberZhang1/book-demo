package com.demo.bookdemo.entity;

import com.demo.bookdemo.entity.base.BaseEntity;

import java.util.Date;

/**
 * 类 名: BookRecord.java
 * 描 述: 租借图书记录
 * 作 者: 张振国
 * 创 建：2018/10/12 22:02
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
public class BookRecord extends BaseEntity {

    /** 图书ID */
    private Long bookId;
    /** 开始时间 */
    private Date startDate;
    /** 结束时间 */
    private Date endDate;
    /** 租书人姓名 */
    private String userName;
    /** 租书人联系方式 */
    private String userPhone;
    /** 状态 0,未还 1 已还 */
    private Integer state;
    /** 租书费用 */
    private Double totalPrice;

    /** 图书信息 */
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

package com.demo.bookdemo.entity;

import com.demo.bookdemo.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * 类 名: Book.java
 * 描 述: 图书类
 * 作 者: lily
 * 创 建：2018/10/12 14:27
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
public class Book extends BaseEntity implements Serializable {

    /** 名称 */
    private String name;
    /** 作者 */
    private String author;
    /** 出版社 */
    private String press;
    /** 总数 */
    private Long total;
    /** 当前库存 */
    private Long current;
    /** 借出数量 */
    private Long amount;
    /** 单价（每分钟） */
    private Double price;
    /** 类型 */
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

















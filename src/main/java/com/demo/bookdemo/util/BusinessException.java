package com.demo.bookdemo.util;

/**
 * 类 名: BusinessException
 * 描 述: 业务异常
 * 作 者: lily
 * 创 建：2018/4/19 下午5:04
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */
public class BusinessException extends Exception{

	public BusinessException(String message){
		super(message);
	}

	public BusinessException(String message, Throwable e){
		super(message,e);
	}

}

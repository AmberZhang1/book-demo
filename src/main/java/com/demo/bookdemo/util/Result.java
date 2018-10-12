package com.demo.bookdemo.util;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * 类名:Result
 * 描述:接口返回结果
 * 创建时间: 2018-03-26
 * 作者: lily
 * 版本: 1.0
 */
public class Result<T> implements Serializable {

	/**
	 * 状态
	 */
	public enum Status{
		ERROR,
		SUCCESS
	}

	/**
	 * 接口调用成功，不需要返回对象
	 */
	public static <T> Result<T> newSuccess(){
		Result<T> result = new Result<>();
		result.setCode(Status.SUCCESS);
		return result;
	}
	
	/**
	 * 接口调用成功，有返回对象
	 */
	public static <T> Result<T> newSuccess(T T) {
		Result<T> result = new Result<>();
		result.setCode(Status.SUCCESS);
		result.setData(T);
		return result;
	}

	/**
	 * 接口调用失败，有错误字符串码和描述，没有返回对象
	 */
	public static <T> Result<T> newFailure(String message){
		Result<T> result = new Result<>();
		result.setCode(Status.ERROR);
		result.setMessage(message);
		return result;
	}

	/**
	 * 接口调用失败，返回异常信息
	 */
	public static <T> Result<T> newException(Exception e){
		Result<T> result = new Result<>();
		result.setCode(Status.ERROR);
		result.setException(e);
		result.setMessage(e.getMessage());
		return result;
	}
	
	private Status code;
	private T data;
	private String message;
	private Exception exception;
	
	/** 判断返回结果是否成功 */
	public boolean success() {
		return Status.SUCCESS.equals(code);
	}
	/** 判断返回结果是否有结果对象 */
	public boolean hasData() {
		return Status.SUCCESS.equals(code) && data != null;
	}
	/** 判断返回结果是否有异常 */
	public boolean hasException() {
		return exception != null;
	}
	
	public Status getCode() {
		return code;
	}
	public void setCode(Status code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T T) {
		this.data = T;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Result");
		if(data != null) {
			result.append("<"+data.getClass().getSimpleName()+">");
		}
		result.append(": {code="+code);
		if(data!=null) {
			result.append(", T="+data);
		}
		if(message!=null) {
			result.append(", message="+message);
		}
		if(exception!=null) {
			StringWriter stringWriter = new StringWriter();
			exception.printStackTrace(new PrintWriter(stringWriter));
			result.append(", exception="+stringWriter.toString());
		}
		result.append(" }");
		return result.toString();
	}
}
package com.zhan.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;


public class BaseHttp {

	/**
	 * 获取request
	 * @return
	 */
	protected HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder
				.getRequestAttributes())
				.getRequest();
	}
	
	/*
	 * 下面是获取session数据的一些代码
	 */
	private static final String SESSION_USER_NAME = "SESSION_USER_NAME";
	private static final String SESSION_USER_ID = "SESSION_USER_ID";
	protected String getSessionUserName(){
		return (String) getRequest().getSession().getAttribute(SESSION_USER_NAME);
	}
	protected String getSessionUserId(){
		return (String) getRequest().getSession().getAttribute(SESSION_USER_ID);
	}
	/*
	 * 下面是获取分页参数的代码
	 */
	private static final String PAGE_NUM = "pageNum";
	private static final String PAGE_SIZE = "pageSize";
	
	protected void startPage(){
		PageHelper.startPage(getPageNum(), getPageSize());
	}

	protected void startPage(int defaultPageNum, int defaultPageSize){
		PageHelper.startPage(getPageNum(defaultPageNum), getPageSize(defaultPageSize));
	}

	protected int getPageNum(int defaultPageNum){
		
		Integer pageNum = getParamInt(PAGE_NUM);
		if(pageNum == null){
			return defaultPageNum;
		}else{
			return pageNum;
		}
	}
	protected int getPageNum(){
		Integer pageNum = getParamInt(PAGE_NUM);
		if(pageNum == null){
			return 1;
		}else{
			return pageNum;
		}
	}
	protected int getPageSize(int defaultPageSize){
		
		Integer pageNum = getParamInt(PAGE_SIZE);
		if(pageNum == null){
			return defaultPageSize;
		}else{
			return pageNum;
		}
		
	}
	protected int getPageSize(){
		Integer pageNum = getParamInt(PAGE_SIZE);
		if(pageNum == null){
			return 10;
		}else{
			return pageNum;
		}
	}
	
	
	protected Integer getParamInt(String name){
		try{
			return Integer.parseInt(getRequest().getParameter(name));
		}catch(Exception e){
			return null;
		}
	}
}

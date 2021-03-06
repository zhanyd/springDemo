package com.zhan.common;

public class StringHelp {
	
	/**
	 * 判断字符串是否为null或者空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value){
		return value == null || value.isEmpty();
	}
	
	
	/**
	 * 转换字符串
	 * @param obj
	 * @return
	 */
	 public static String valueOf(Object obj) {
	        return (obj == null) ? null : obj.toString();
	    }
	 
	 /**
	  * 转换int 
	  * @param obj
	  * @return
	  */
	 public static int parseInt(String obj){
		 try{
			 return Integer.parseInt(obj);
		 }catch(Exception e){
			 return 0;
		 }
	 }
	

}

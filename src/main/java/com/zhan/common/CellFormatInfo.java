package com.zhan.common;

public class CellFormatInfo {

	private int width;
	private int height;
	private String key;
	private String title;
	
	public CellFormatInfo(String key,String title,int width,int height){
		setKey(key);
		setTitle(title);
		setWidth(width);
		setHeight(height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

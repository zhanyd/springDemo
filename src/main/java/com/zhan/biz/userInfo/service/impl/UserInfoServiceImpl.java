package com.zhan.biz.userInfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhan.biz.userInfo.dao.UserInfoMapper;
import com.zhan.biz.userInfo.model.UserInfo;
import com.zhan.biz.userInfo.service.UserInfoService;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService{

	/*@Autowired
	UserInfoMapper userInfoMapper;*/
	
	@Resource
	UserInfoMapper userInfoMapper;
	
	public List<UserInfo> selectAll(){
		return userInfoMapper.selectAll();
	}
}

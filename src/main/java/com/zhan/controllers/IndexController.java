package com.zhan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhan.biz.userInfo.model.UserInfo;
import com.zhan.biz.userInfo.service.UserInfoService;

@Controller
@RequestMapping("/view/Index")
public class IndexController {

	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping(value = "/goIndex")
	public ModelAndView goIndex(){
		ModelAndView model = new ModelAndView("/index");
		List<UserInfo> userList = userInfoService.selectAll();
		userList.stream().forEach(i->System.out.print(i.getName() + " " + i.getAge() + " " + i.getAddress() + "\n"));
		return model;
	}
}

package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.component.UI;
import com.frame.Biz;
import com.vo.User;

@Controller
public class UserController {
	@Autowired
	@Qualifier("userbiz")
	Biz<String, User> biz;
	
	@RequestMapping("/useradd.mc")
	public ModelAndView userAdd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		UI.build(mv, "useradd");
		
		return mv;
	}
	
	@RequestMapping("/useraddimpl.mc")
	public ModelAndView userAddImpl(ModelAndView mv, User user) {
		mv.setViewName("main");
		
		try {
			biz.insert(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@RequestMapping("/userlist.mc")
	public ModelAndView userList(ModelAndView mv) {
		mv.setViewName("main");
		
		try {
			ArrayList<User> users = biz.selectAll();
			mv.addObject("ulist", users);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UI.build(mv, "userlist");
		
		return mv;
	}
}

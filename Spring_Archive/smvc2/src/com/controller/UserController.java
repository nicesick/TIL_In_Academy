package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.component.UI;

@Controller
public class UserController {
	
	@RequestMapping("/useradd.mc")
	public ModelAndView userAdd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		UI.build(mv, "useradd");
		
		return mv;
	}
	
	@RequestMapping("/userlist.mc")
	public ModelAndView userList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		UI.build(mv, "userlist");
		
		return mv;
	}
}

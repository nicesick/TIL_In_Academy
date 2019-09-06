package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.component.UI;
import com.frame.Biz;
import com.vo.User;

@Controller
public class MainController {
	@Autowired
	@Qualifier("userbiz")
	Biz<String, User> biz;
	
	@RequestMapping("main.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");

		return mv;
	}

	@RequestMapping("login.mc")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");

		UI.build(mv, "login");

		return mv;
	}

	@RequestMapping("loginimpl")
	public ModelAndView loginImpl(ModelAndView mv, 
			HttpServletRequest request,
			HttpSession session) {
		mv.setViewName("main");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		
		User user = null;
		
		try {
			user = biz.select(id);
			
			if (user != null) {
				if (user.getPwd().equals(pwd)) {
					mv.addObject("center", "loginok");
					session.setAttribute("loginuser", user);
				}
				
				else {
					mv.addObject("center", "loginfail");
				}
			}
			
			else {
				mv.addObject("center", "loginfail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}

	@RequestMapping("register.mc")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");

		UI.build(mv, "register");

		return mv;
	}

	@RequestMapping("aboutus.mc")
	public ModelAndView aboutUs() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");

		UI.build(mv, "aboutus");

		return mv;
	}
	
	@RequestMapping("logout.mc")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		mv.setViewName("main");
		
		if(session != null) {
			session.invalidate();
		}
		
		return mv;
	}
}

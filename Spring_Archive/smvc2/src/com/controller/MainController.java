package com.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.component.UI;


@Controller
public class MainController {
	
	@RequestMapping("main.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		return mv;
	}
	
	@RequestMapping("login.mc")
	public ModelAndView login(WebRequest wr) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		UI.build(mv, "login");
		
		System.out.println(wr.getParameter("id"));
		
//		Map<String, Object> datas = mv.getModel();
//		Set<String> keys = datas.keySet();
//		
//		Iterator<String> iter = keys.iterator();
//		
//		while(iter.hasNext()) {
//			String key = iter.next();
//			
//			System.out.println(key);
//			System.out.println(datas.get(key));
//		}
//		
//		if (datas.containsKey("id") && datas.containsKey("pwd") && datas.containsKey("name")) {
//			System.out.println(datas.get("id") + " : " + datas.get("pwd") + " : " + datas.get("name"));
//		}
		
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
}

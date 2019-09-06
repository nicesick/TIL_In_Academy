package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.component.UI;

@Controller
public class ProductController {
	
	@RequestMapping("/productadd.mc")
	public ModelAndView productAdd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		UI.build(mv, "productadd");
		
		return mv;
	}
	
	@RequestMapping("/productlist.mc")
	public ModelAndView productList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		UI.build(mv, "productlist");
		
		return mv;
	}
}

package com.component;

import org.springframework.web.servlet.ModelAndView;

public class UI {
	// make ui
	public static void build(ModelAndView mv, String view) {
		if (view.equals("login")) {
			mv.addObject("navi", Navi.login);
			mv.addObject("center", "login");
		} 
		
		else if (view.equals("register")) {
			mv.addObject("navi", Navi.register);
			mv.addObject("center", "register");
		}
		
		else if (view.equals("aboutus")) {
			mv.addObject("navi", Navi.aboutus);
			mv.addObject("center", "aboutus");
		}
		
		else if (view.equals("useradd")) {
			mv.addObject("navi", Navi.useradd);
			mv.addObject("center", "user/add");
		}
		
		else if (view.equals("productadd")) {
			mv.addObject("navi", Navi.productadd);
			mv.addObject("center", "product/add");
		}
		
		else if (view.equals("productlist")) {
			mv.addObject("navi", Navi.productlist);
			mv.addObject("center", "product/list");
		}
		
		else if (view.equals("userlist")) {
			mv.addObject("navi", Navi.userlist);
			mv.addObject("center", "user/list");
		}
	}
}

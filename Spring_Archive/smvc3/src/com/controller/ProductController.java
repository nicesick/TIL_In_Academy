package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.component.UI;
import com.frame.Biz;
import com.vo.Product;

@Controller
public class ProductController {
	@Autowired
	@Qualifier("productbiz")
	Biz<Integer, Product> biz;
	
	@RequestMapping("/productadd.mc")
	public ModelAndView productAdd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		
		UI.build(mv, "productadd");
		
		return mv;
	}
	
	@RequestMapping("/productlist.mc")
	public ModelAndView productList(ModelAndView mv) {
		mv.setViewName("main");
		
		try {
			ArrayList<Product> products = biz.selectAll();
			mv.addObject("plist", products);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UI.build(mv, "productlist");
		
		return mv;
	}
	
	@RequestMapping("/productaddimpl.mc")
	public ModelAndView productAddImpl(ModelAndView mv, Product product) {
		mv.setViewName("main");
		
		String imgname = product.getMf().getOriginalFilename();
		product.setImgname(imgname);
		
		try {
			biz.insert(product);
			Util.saveFile(product.getMf());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
}

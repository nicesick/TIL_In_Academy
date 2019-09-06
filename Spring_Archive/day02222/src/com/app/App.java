package com.app;

import java.util.Date;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.vo.Product;
import com.vo.User;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("mySpring.xml");
		Biz<String, User> ubiz = (Biz<String, User>) factory.getBean("userbiz");
		Biz<String, Product> pbiz = (Biz<String, Product>) factory.getBean("productbiz");
		
		try {
			ubiz.insert(new User("id00","ÁöÈÆ","¶¯Ä¥"));
			ubiz.select("id00");
			
			pbiz.update("id01", new Product("id01","ÁöÈÆÀÌ",1000000d, new Date(), "¶¯Ä¥ÀÌ"));
			pbiz.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
}

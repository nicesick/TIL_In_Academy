package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.vo.Product;
import com.vo.User;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		Biz<String, User> ubiz = (Biz<String, User>) factory.getBean("userbiz");
		Biz<Integer, Product> pbiz = (Biz<Integer, Product>) factory.getBean("productbiz");
		
		try {
			System.out.println(ubiz.selectAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
}

package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.user.User;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		Biz<String, User> biz = (Biz<String, User>) factory.getBean("userbiz");
		
		User user = new User("id01","pwd01","이마랒라자");
		
		try {
			biz.insert(user);
			System.out.println("Inserted Ok from App");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		factory.close();
	}
}

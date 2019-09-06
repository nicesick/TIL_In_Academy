package com.app;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.frame.Search;
import com.vo.Product;
import com.vo.User;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("mySpring.xml");
		Search<String, User> ubiz = (Search<String, User>) factory.getBean("userbiz");
		Biz<Integer, Product> pbiz = (Biz<Integer, Product>) factory.getBean("productbiz");
		
		try {
			System.out.println(ubiz.search("¶¯"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
}

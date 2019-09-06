package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.product.Product;
import com.user.User;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		Biz<String, User> ubiz = (Biz<String, User>) factory.getBean("userbiz");
		//Biz<String, Product> pbiz = (Biz<String, Product>) factory.getBean("productbiz");
		
		User user = new User("id01","pwd01","ÁöÈÆÂ¯Â¯");
		
		try {
			user = ubiz.select("¶¯Ä¥ÀÌ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * User user = new User("id01","pwd01","ÀÌ¸¶¶ü¶óÀÚ"); Product p = new
		 * Product("¶¯","Ä¥",10000D, "ÀÌ");
		 * 
		 * try { ubiz.insert(user); pbiz.insert(p);
		 * 
		 * System.out.println("Inserted Ok from App"); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		
		factory.close();
	}
}

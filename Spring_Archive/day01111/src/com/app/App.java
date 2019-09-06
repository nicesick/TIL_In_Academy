package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;

public class App {

	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("databaseControl.xml");
		
		Biz biz = (Biz) factory.getBean("userbiz");
		biz.register();
		
		factory.close();
	}
}

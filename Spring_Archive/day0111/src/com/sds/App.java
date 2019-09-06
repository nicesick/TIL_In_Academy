package com.sds;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		
		TV stv = (TV) factory.getBean("stv");
		System.out.println(stv.toString());
		stv.up();
		
		TV ltv = (TV) factory.getBean("ltv");
		System.out.println(ltv.toString());
		
		factory.close();
	}
}

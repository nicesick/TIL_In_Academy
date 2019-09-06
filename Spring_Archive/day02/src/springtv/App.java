package springtv;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		AbstractApplicationContext factory = 
		new GenericXmlApplicationContext("mytv.xml");
		
		System.out.println("Spring Started");
		
		TV stv = (TV)factory.getBean("ltv");
		stv.turnOn();
		stv.volumeUp(10);
		System.out.println(stv);
		
		factory.close();
	}
}
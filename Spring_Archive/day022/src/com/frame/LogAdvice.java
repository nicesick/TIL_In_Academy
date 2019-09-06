package com.frame;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LogAdvice {
	public void printLog() {
		Date d = new Date();
		System.out.println(d + " : in prograss...");
	}
	
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		System.out.println(method + " : " + args[0]);
		System.out.println("BEFORE : in prograss...");
	}
	
	public void afterLog(JoinPoint jp, Object returnObj) {
		System.out.println(returnObj);
		System.out.println("AFTER : in prograss...");
	}
	
	public void exLog(JoinPoint jp, Object ex) {
		System.out.println(jp.getSignature().getName());
		System.out.println(Arrays.toString(jp.getArgs()));
		
		Date d = new Date();
		System.out.println(d + " : Exception...");
	}
	
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object obj = pjp.proceed();
		
		Thread.sleep(2800);
		stopWatch.stop();
		
		System.out.println("Result : " + stopWatch.getId());
		System.out.println("Result : " + stopWatch.getTotalTimeSeconds());
		
		return obj;
	}
}

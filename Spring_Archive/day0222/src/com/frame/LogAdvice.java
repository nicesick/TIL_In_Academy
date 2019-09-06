package com.frame;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class LogAdvice {
	public void printLog() {
		Date d = new Date();
		System.out.println(d + " : in prograss...");
	}
	
	@Before("execution(* com..*Biz.insert(..))")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		System.out.println(method + " : " + args[0]);
		System.out.println("BEFORE : in prograss...");
	}
	
	@AfterReturning(pointcut="execution(* com..*Biz.select(..))",
					returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		System.out.println(returnObj);
		System.out.println("AFTER : in prograss...");
	}
	
	@AfterThrowing(pointcut="execution(* com..*Biz.select(..))",
				   throwing="ex")
	public void exLog(JoinPoint jp, Object ex) {
		System.out.println(jp.getSignature().getName());
		System.out.println(Arrays.toString(jp.getArgs()));
		
		Date d = new Date();
		System.out.println(d + " : Exception...");
	}
	
	@Around("execution(* com..*Biz.*(..))")
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

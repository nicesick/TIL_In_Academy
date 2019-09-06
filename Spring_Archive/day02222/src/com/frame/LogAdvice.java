package com.frame;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogAdvice {
	
	@Before("execution(* com..*Biz.*(..))")
	public void beforeLog(JoinPoint jp) {
		System.out.println("LOGADVICE : BEFORE");
		
		System.out.println("METHODNAME : " + jp.getSignature().getName());
		System.out.println("ARGS : " + Arrays.toString(jp.getArgs()));
	}
	
	@AfterReturning(pointcut="execution(* com..*Biz.select*(..))",
					returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		System.out.println("LOGADVICE : AFTERRETURNING");
		
		System.out.println("METHODNAME : " + jp.getSignature().getName());
		System.out.println("ARGS : " + Arrays.toString(jp.getArgs()));
		System.out.println("RETURN : " + returnObj);
	}
	
	@AfterThrowing(pointcut="execution(* com..*Biz.*(..))",
					throwing="ex")
	public void exLog(JoinPoint jp, Exception ex) {
		System.out.println("LOGADVICE : AFTERTHROWING");
		
		System.out.println("METHODNAME : " + jp.getSignature().getName());
		System.out.println("ARGS : " + Arrays.toString(jp.getArgs()));
		System.out.println("EXCEPTION : " + ex.getMessage());
	}
	
	@Around("execution(* com..*Biz.*(..))")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("LOGADVICE : AROUND START");
		
		System.out.println("METHODNAME : " + pjp.getSignature().getName());
		System.out.println("ARGS : " + Arrays.toString(pjp.getArgs()));
		
		Object obj = pjp.proceed();
		
		System.out.println("RESULT : " + obj);
		
		System.out.println("LOGADVICE : AROUND DONE");
		
		return obj;
	}
}

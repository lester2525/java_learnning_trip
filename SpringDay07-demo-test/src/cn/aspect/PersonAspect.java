package cn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class PersonAspect {
	
	@Around(value="execution(* cn.servlet..*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("类名："+joinPoint.getTarget().getClass());
		System.out.println("方法名："+joinPoint.getSignature().getName());
		long start = System.currentTimeMillis();
		Object object = joinPoint.proceed();
		long end = System.currentTimeMillis();
		System.out.println("程序运行时间为：" +(end-start)+"ms");
		return object;
	}
}

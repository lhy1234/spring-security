package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 调用耗时切片
 * ClassName: TimeAspect 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月26日
 */
//@Aspect
//@Component
public class TimeAspect {

	/**
	 * 
	 */
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
		System.err.println(">>>> 进入  TimeAspect start  >>>>>");
		
		Object[] args = pjp.getArgs();//调用
		if(args.length > 0){
			for (Object arg : args) {
				System.err.println("arg is "+arg);
			}
		}
		long start = new Date().getTime();
		
		Object object = pjp.proceed();
		System.err.println("TimeAspect 调用耗时："+(new Date().getTime()-start));
		System.err.println(">>>>   TimeAspect 结束  >>>>>");
		
		return object;
	}
}

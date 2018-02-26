package com.imooc.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 记录调用耗时的拦截器
 * ClassName: TimeInterceptor 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月26日
 */
@Component //声明为spring组件
public class TimeInterceptor implements HandlerInterceptor{

	//进入controller之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.err.println("++++++ 进入 preHandle...+++++++");
		
		System.err.println(((HandlerMethod)handler).getBean().getClass().getName());//哪个类
		System.err.println(((HandlerMethod)handler).getMethod()); //哪个方法
		request.setAttribute("startTime", new Date().getTime());//调用开始计时
		return true;
	}

	//进入controller之中执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.err.println("postHandle...");
		Long start = (Long) request.getAttribute("startTime");
		System.err.println("time interceptor 耗时："+(new Date().getTime()-start));
	}

	//controller执行完之后执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		Long start = (Long) request.getAttribute("startTime");
		System.err.println("time interceptor 耗时："+(new Date().getTime()-start));
		//如果调用有异常，这个Exception会有信息
		System.err.println("exception:"+ex);
		
		System.err.println("+++++ afterCompletion +++++++");
	}

}

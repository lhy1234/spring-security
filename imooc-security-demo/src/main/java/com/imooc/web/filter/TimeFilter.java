package com.imooc.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 * 记录执行时间过滤器
 * ClassName: TimeFilter 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月26日
 */
//@Component //声明为spring组件，springboot项目没有web.xml直接声明为组件
public class TimeFilter implements Filter {

	@Override
	public void destroy() {
		System.err.println("time filter destory...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.err.println("=======time filter start======");
		long startTime = new Date().getTime();
		chain.doFilter(request, response); //执行其他过滤器链
		System.err.println("time filter 耗时："+(new Date().getTime()-startTime));
		System.err.println("=======time filter end=======");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.err.println("time filter init...");
	}

}

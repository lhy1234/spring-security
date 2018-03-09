package com.imooc.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * session失效策略，简单版本
 * ClassName: ImoocExpiredSessionStrategy 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月8日
 */
public class ImoocExpiredSessionStrategy2 implements SessionInformationExpiredStrategy{

	/**
	 * SessionInformationExpiredEvent:session失效事件，能拿到request、response
	 */
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		//可以从event中拿到request中的信息
		event.getResponse().setContentType("application/json;charset=UTF-8");
		event.getResponse().getWriter().write("并发登录！");
	}
	
	
	

}

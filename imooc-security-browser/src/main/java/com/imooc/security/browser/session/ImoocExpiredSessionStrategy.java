package com.imooc.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * session失效策略
 * ClassName: ImoocExpiredSessionStrategy 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月8日
 */
public class ImoocExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy
			{

	/**
	 * SessionInformationExpiredEvent:session失效事件，能拿到request、response
	 */
//	@Override
//	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
//		event.getResponse().setContentType("application/json;charset=UTF-8");
//		event.getResponse().getWriter().write("并发登录！");
//	}
	
	public ImoocExpiredSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.session.SessionInformationExpiredStrategy#onExpiredSessionDetected(org.springframework.security.web.session.SessionInformationExpiredEvent)
	 */
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}
	
	/* (non-Javadoc)
	 * @see com.imooc.security.browser.session.AbstractSessionStrategy#isConcurrency()
	 */
	@Override
	protected boolean isConcurrency() {
		return true;
	}

}

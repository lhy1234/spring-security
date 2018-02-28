package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 模拟消息队列
 * ClassName: MockQueue 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月27日
 */
@Component
public class MockQueue {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String placeOrder; //下单动作
	
	private String completeOrder;//下单完成

	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) throws Exception {
		logger.info("---->>接收到下单请求，"+placeOrder);
		Thread.sleep(1000);
		this.completeOrder = placeOrder; //模拟下单成功，给completeOrder赋值即可
		logger.info("---->>下单请求处理完成，"+placeOrder);
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
}

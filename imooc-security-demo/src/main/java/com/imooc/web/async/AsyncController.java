package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 
 * ClassName: AsynController 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月27日
 */
@RestController
public class AsyncController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@RequestMapping("/order")
	public Callable<String> order() throws Exception{
		System.err.println("-------进入AsynController------");
		logger.info("主线程开始");
		Callable<String> result = new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info("++ 副线程开始");
				Thread.sleep(1000);
				logger.info("++ 副线程返回");
				return "success";
			}
		};
		logger.info("主线程返回");
		return result;
	}
	
	@RequestMapping("/order2")
	public DeferredResult<String> order2() throws Exception{
		System.err.println("-------进入AsynController order2 ------");
		logger.info("主线程开始");
		//8位随机数模拟订单号
		String orderNumber = RandomStringUtils.randomNumeric(8);
		//讲订单号放入消息队列，并下单成功
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> result = new DeferredResult<String>();
		deferredResultHolder.getMap().put(orderNumber, result);
		logger.info("主线程返回");
		
		return result;
	}
	
}

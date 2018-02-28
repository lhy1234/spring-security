package com.imooc.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 模拟监听消息队列，监听是否有完成的订单，有就处理，没有继续监听
 * ClassName: QueueListener 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月27日
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
			
		new Thread(()->{
			//模拟监听消息队列，监听是否有完成的订单，有就处理，没有继续监听
			while(true){
				//CompleteOrder有值说明有完成的订单
				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
					String orderNumber = mockQueue.getCompleteOrder();
					logger.info("返回订单处理结果，订单号："+orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
					mockQueue.setCompleteOrder(null);//退出循环
				}else{
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

}

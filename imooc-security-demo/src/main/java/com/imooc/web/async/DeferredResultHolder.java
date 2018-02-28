package com.imooc.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class DeferredResultHolder {
	
	//	模拟	Map<订单号，订单处理结果>	DeferredResult<String>:返回字符串	 
	private Map<String,DeferredResult<String>> map = new HashMap<String,DeferredResult<String>>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}

	
}

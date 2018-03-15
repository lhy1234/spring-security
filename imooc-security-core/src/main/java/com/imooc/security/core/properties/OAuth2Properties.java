package com.imooc.security.core.properties;

/**
 * 多个接口客户端，是数组，只有一个的话就不用这个了
 * ClassName: OAuth2Properties 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月15日
 */
public class OAuth2Properties {
	
	private OAuth2ClientProperties[] clients = {};

	public OAuth2ClientProperties[] getClients() {
		return clients;
	}

	public void setClients(OAuth2ClientProperties[] clients) {
		this.clients = clients;
	}
	
	

}

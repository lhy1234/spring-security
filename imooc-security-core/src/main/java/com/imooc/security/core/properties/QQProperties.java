package com.imooc.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * QQ登录相关配置
 * ClassName: QQProperties 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月7日
 */
public class QQProperties extends SocialProperties {

	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
	
}

package com.imooc.security.core.properties;

/**
 * 第三方登录相关配置
 * ClassName: SocialProperties 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月7日
 */
public class SocialProperties {

	private QQProperties qq = new QQProperties();

	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}
	
	
}

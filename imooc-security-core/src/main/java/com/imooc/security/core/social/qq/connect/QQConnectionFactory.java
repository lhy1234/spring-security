package com.imooc.security.core.social.qq.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import com.imooc.security.core.social.qq.api.QQ;

/**
 * 创建Connection工厂
 * ClassName: QQConnectionFactory 
 * @Description: 
 * 		创建Connection工厂
 * 		继承默认实现 OAuth2ConnectionFactory
 * 		泛型：Api是什么，就是我们的QQ
 * @author lihaoyang
 * @date 2018年3月7日
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 *  * 需要两个对象：
	 * 	1，ServiceProvider --> QQServieProvider
	 * 	2，ApiAdapter      --> QQApiAdapter
	 * <p>Description: </p>
	 * @param providerId 
	 * @param appId
	 * @param appSecret
	 */
	public QQConnectionFactory(String providerId, String appId , String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
		
	}

}

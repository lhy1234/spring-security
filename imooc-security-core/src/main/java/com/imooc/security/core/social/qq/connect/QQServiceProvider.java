package com.imooc.security.core.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQImpl;


/**
 * QQ服务提供商
 * ClassName: QQServiceProvider 
 * @Description: 
 *  需要继承spring social的默认实现AbstractOAuth2ServiceProvider
 *  泛型是指获取用户信息的Api，类型就是QQ
 * @author lihaoyang
 * @date 2018年3月6日
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{

	private String appId;
	
	//将用户引导到获取授权码的地址
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	//拿着授权码申请令牌token的地址
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	/**
	 *  返回ServiceProvider需要的OAuthOperations
	 * <p>Description: </p>
	 * @param appId 不同的应用是不一样的
	 * @param appSecret  不同的应用是不一样的
	 */
	public QQServiceProvider(String appId , String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		
	}

	/**
	 *  返回ServiceProvider需要的Api
	 */
	@Override
	public QQ getApi(String accessToken) {
		
		return new QQImpl(accessToken, appId);
	}

}

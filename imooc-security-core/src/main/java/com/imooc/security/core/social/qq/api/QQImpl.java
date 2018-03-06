package com.imooc.security.core.social.qq.api;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * 流程中的Api。
 * ClassName: QQImpl 
 * @Description: 
 * ***********注意*************
 * 这个是多例的，每个用户不一样进来他们的accessToken、openid是不一样的
 * 所以不能@Component声明为spring组件！！！
 * ***********注意*************
 * @author lihaoyang
 * @date 2018年3月6日
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{

	/**
	 * 调用qq的get_user_info
	 * 1：3个参数，OAuth2.0协议的通用三个参数：
	 *  access_token: 父类已提供
	 *  appid://申请QQ登录成功后，分配给应用的appid
	 *  openid://用户的ID，与QQ号码一一对应。 
	 *  
	 *  2：2个路径
	 *  获取openid的路径：
	 *  	https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN
	 *  获取用户信息的路径：
	 *  	https://graph.qq.com/user/get_user_info?access_token=*************&oauth_consumer_key=12345&openid=
	 */
	
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	private static final String URL_GET_USRE_INFO = "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=12345&openid=%s";
	
	private String appId;
	
	private String openId;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 实例化时获取openid
	 * <p>Description: </p>
	 * @param accessToken
	 * @param appId
	 */
	public QQImpl(String accessToken , String appId){
		//父类默认构造会把accessToken放在请求头里，这是不符合qq要求的放在url参数里的，所以掉一下作为参数的构造
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		
		this.appId  = appId;
		
		String url = String.format(URL_GET_USRE_INFO, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);//调用父类的restTemplate发请求，获取openid
		
		System.err.println(result);
		//{"client_id":"YOUR_APPID","openid":"YOUR_OPENID"}
		//截取openid
		this.openId = StringUtils.substringBetween(result, "\"openid\"", "}");
	}
	
	@Override
	public QQUserInfo getUserInfo(){
		//accessToken已在父类挂在了参数
		String url = String.format(URL_GET_USRE_INFO, appId,openId);
		String result = getRestTemplate().getForObject(url, String.class);
		
		System.err.println(result);
		try {
			return objectMapper.readValue(result, QQUserInfo.class);
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败",e);
		} 
	}

}

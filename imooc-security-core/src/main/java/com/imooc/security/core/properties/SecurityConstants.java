package com.imooc.security.core.properties;

/**
 * 安全常量配置
 * ClassName: SecurityConstants 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月8日
 */
public interface SecurityConstants {
	
	/**
	 * 默认的登录页
	 */
	public static final String DEFAULT_LOGIN_PAGE = "/demo-login.html";
	
	/**
	 * session失效时的处理
	 */
	public static final String SESSION_INVALID_PAGE = "/session/invalid";
	
	/**
	 * session失效时的跳转的页面
	 */
	public static final String DEFAULT_SESSION_INVALID_URL = "/imooc-session-invalid.html";
	
	/**
	 * 需要身份认证时，默认跳转的url
	 */
	public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
	
	/**
	 * 默认的用户名密码登录请求处理的url
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
	
	/**
	 * 默认的手机验证码登录请求处理的url
	 */
	public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
	
	/**
	 * 默认的验证码请求的前缀
	 */
	public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/verifycode";
	
	/**
	 * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
	/**
	 * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	/**
	 * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
	 */
	public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

}

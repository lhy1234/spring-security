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
	public static final String DEFAULT_LOGIN_PAGE = "/login.html";
	
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

}

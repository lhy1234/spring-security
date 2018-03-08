package com.imooc.security.core.properties;

/**
 * 浏览器配置项
 * ClassName: BrowserProperties 
 * @Description: 浏览器配置项
 * @author lihaoyang
 * @date 2018年2月28日
 */
public class BrowserProperties {

	//用户未配置默认登录页
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE; 

	//登录类型，返回json或还是重定向
	private LoginType loginType = LoginType.JSON ;
	
	//记住我秒数配置
	private int rememberMeSeconds = 3600;  
	
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
}

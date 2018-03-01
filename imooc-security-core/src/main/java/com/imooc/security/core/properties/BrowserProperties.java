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
	private String loginPage = "/login.html"; 

	private LoginType loginType = LoginType.JSON ;
	
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
	
}

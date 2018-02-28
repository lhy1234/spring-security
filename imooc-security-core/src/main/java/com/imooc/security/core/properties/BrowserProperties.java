package com.imooc.security.core.properties;

/**
 * 浏览器配置项
 * ClassName: BrowserProperties 
 * @Description: 浏览器配置项
 * @author lihaoyang
 * @date 2018年2月28日
 */
public class BrowserProperties {

	private String loginPage = "/login.html"; //用户未配置默认登录页

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
}

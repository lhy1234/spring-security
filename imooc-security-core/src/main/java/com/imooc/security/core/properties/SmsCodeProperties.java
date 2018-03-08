package com.imooc.security.core.properties;

/**
 * 短信验证码配置类
 * ClassName: ImageCodeProperties 
 * @Description: 图片验证码配置类
 * @author lihaoyang
 * @date 2018年3月2日
 */
public class SmsCodeProperties {

	
	//验证码字符个数
	private int length = 4;
	//过期时间
	private int expireIn = 60;
	
	private String url; //拦截的url



	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

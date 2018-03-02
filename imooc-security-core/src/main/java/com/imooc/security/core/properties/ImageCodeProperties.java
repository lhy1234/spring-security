package com.imooc.security.core.properties;

/**
 * 图片验证码配置类
 * ClassName: ImageCodeProperties 
 * @Description: 图片验证码配置类
 * @author lihaoyang
 * @date 2018年3月2日
 */
public class ImageCodeProperties {

	//图片宽
	private int width = 67;
	//图片高
	private int height = 23;
	//验证码字符个数
	private int length = 4;
	//过期时间
	private int expireIn = 60;
	
	private String url; //拦截的url

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

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

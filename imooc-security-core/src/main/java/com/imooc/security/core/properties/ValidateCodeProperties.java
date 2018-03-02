package com.imooc.security.core.properties;

/**
 * 验证码配置
 * ClassName: ValidateCodeProperties 
 * @Description: 验证码配置,验证码有图片验证码、短信验证码等，所以再包一层
 * @author lihaoyang
 * @date 2018年3月2日
 */
public class ValidateCodeProperties {
	
	//默认配置
	private ImageCodeProperties image = new ImageCodeProperties();

	public ImageCodeProperties getImage() {
		return image;
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}

	
	
}

package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 * ClassName: ImageCode 
 * @Description: 验证码
 * @author lihaoyang
 * @date 2018年3月1日
 */
public class ImageCode extends ValidateCode{

	private BufferedImage image;
	
	/**
	 * 
	 * <p>Description: </p>
	 * @param image
	 * @param code
	 * @param expireTn 多少秒过期
	 */
	public ImageCode(BufferedImage image, String code, int expireTn) {
		super(code,expireTn);
		this.image = image;
	}

	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code,expireTime);
		this.image = image;
	}

	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	
}

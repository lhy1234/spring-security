package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成接口
 * ClassName: ValidateCodeGenerator 
 * @Description: 所有验证码具体实现的接口
 * @author lihaoyang
 * @date 2018年3月2日
 */
public interface ValidateCodeGenerator {

	/**
	 * 图片验证码生成接口
	 * @Description: TODO
	 * @param @param request
	 * @param @return   
	 * @return ImageCode  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年3月2日
	 */
	ImageCode generator(ServletWebRequest request);
}

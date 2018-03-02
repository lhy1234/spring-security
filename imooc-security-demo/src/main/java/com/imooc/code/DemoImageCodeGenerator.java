package com.imooc.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.ImageCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;

@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode generator(ServletWebRequest request) {
		System.err.println("demo项目实现的生成验证码，，，");
		
		return null;
	}

}

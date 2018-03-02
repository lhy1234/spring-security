package com.imooc.security.core.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;

/**
 * 验证码Control
 * ClassName: ValidateCodeController 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月1日
 */
@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";	
	
//	@Autowired
//	private SecurityProperties securityProperties;
	
	@Autowired
	private ImageCodeGenerator imageCodeGenerator;
	
	//获取session
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@GetMapping("/verifycode/image")
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
//		ImageCode imageCode = createImageCode(request, response);
		//调图片生成接口方式
		ImageCode imageCode = imageCodeGenerator.generator(new ServletWebRequest(request));
		
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}

	
//	private ImageCode createImageCode(HttpServletRequest request, HttpServletResponse response) {
//		//先从request里读取有没有长、宽、字符个数参数，有的话就用，没有用默认的
//		int width  = ServletRequestUtils.getIntParameter(request, "width",securityProperties.getCode().getImage().getWidth());
//    	
//    	int height = ServletRequestUtils.getIntParameter(request, "height",securityProperties.getCode().getImage().getHeight());
//    	
//    	int charLength = this.securityProperties.getCode().getImage().getLength();
//		VerifyCode verifyCode = new VerifyCode(width,height,charLength);
//		return new ImageCode(verifyCode.getImage(),verifyCode.getText(),this.securityProperties.getCode().getImage().getExpireIn());
//	}

}

package com.imooc.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.BrowserProperties;
import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;


/**
 * 认证成功后做的处理
 * ClassName: ImoocAuthenticationSuccessHandler 
 * @Description:  认证成功后做的处理
 * @author lihaoyang
 * @date 2018年3月1日
 */
@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler 
		//spring默认的登录成功处理器，实现了AuthenticationSuccessHandler接口，适配登录后 重定向和返回json两种用这个实现
		extends SavedRequestAwareAuthenticationSuccessHandler 
		//返回json用这个实现
		/*implements AuthenticationSuccessHandler*/{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//springmvc启动会自动注册一个ObjectMapper
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");
		
		/**
		 * 判断配置的登录类型，做不同处理
		 */
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
			//响应json
			//把authentication返回给响应
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else{
			//调用父类的方法，默认就是重定向
			super.onAuthenticationSuccess(request, response, authentication);
		}
		
	}

}

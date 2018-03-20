package com.imooc.security.core.authentication.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * 权限配置默认实现
 * ClassName: ImoocAuthorizeConfigProvider 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月20日
 */
//@Component
public class ImoocAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void config(
			ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL, 
				securityProperties.getBrowser().getLoginPage(),//放过登录页不过滤，否则报错
				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
				SecurityConstants.SESSION_INVALID_PAGE,
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll();
	}

}

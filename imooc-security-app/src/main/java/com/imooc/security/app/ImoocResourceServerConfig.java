package com.imooc.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.SmsCodeFilter;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import com.imooc.security.core.validate.code.ValidateCodeRepository;

/**
 * 资源服务器，和认证服务器在物理上可以在一起也可以分开
 * ClassName: ImoocResourceServerConfig 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月13日
 */
@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter{

	//自定义的登录成功后的处理器
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	//自定义的认证失败后的处理器
	@Autowired
	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	//读取用户配置的登录页配置
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeRepository validateCodeRepository;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
	
		//~~~-------------> 图片验证码过滤器 <------------------
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setValidateCodeRepository(validateCodeRepository);
		//验证码过滤器中使用自己的错误处理
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		//配置的验证码过滤url
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		
		//~~~-------------> 短信验证码过滤器 <------------------
		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
		smsCodeFilter.setValidateCodeRepository(validateCodeRepository);
		//验证码过滤器中使用自己的错误处理
		smsCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		//配置的验证码过滤url
		smsCodeFilter.setSecurityProperties(securityProperties);
		smsCodeFilter.afterPropertiesSet();

		http 
		//短信验证码过滤器
		.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//		.apply(imoocSocialSecurityConfig)//社交登录
//		.and()
		//把验证码过滤器加载登录过滤器前边
		.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
		
		//----------表单认证相关配置---------------
		.formLogin() 
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) //处理用户认证BrowserSecurityController
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) 
			.successHandler(imoocAuthenticationSuccessHandler)//自定义的认证后处理器
			.failureHandler(imoocAuthenticationFailureHandler) //登录失败后的处理
			.and() 
		//-----------授权相关的配置 ---------------------
		.authorizeRequests()  
			// /authentication/require：处理登录，securityProperties.getBrowser().getLoginPage():用户配置的登录页
			.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL, 
			securityProperties.getBrowser().getLoginPage(),//放过登录页不过滤，否则报错
			SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
			SecurityConstants.SESSION_INVALID_PAGE,
			SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll() //验证码
			.anyRequest()		//任何请求
			.authenticated()	//都需要身份认证
		.and()
			.csrf().disable() //关闭csrf防护
		.apply(smsCodeAuthenticationSecurityConfig);//把短信验证码配置应用上
		
	}

	
}

package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.imooc.security.core.properties.SecurityProperties;

@Configuration //这是一个配置
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//读取用户配置的登录页配置
	@Autowired
	private SecurityProperties securityProperties;
	
	//自定义的登录成功后的处理器
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	//自定义的认证失败后的处理器
	@Autowired
	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

	//注意是org.springframework.security.crypto.password.PasswordEncoder
	@Bean
	public PasswordEncoder passwordencoder(){
		//BCryptPasswordEncoder implements PasswordEncoder
		return new BCryptPasswordEncoder();
	}
	
	
	
	//版本一：配置死的登录页
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		//实现需要认证的接口跳转表单登录,安全=认证+授权
//		//http.httpBasic() //这个就是默认的弹框认证
//		http.formLogin() //表单认证
//			.loginPage("/login.html") //登录页面
//			//登录过滤器UsernamePasswordAuthenticationFilter默认登录的url是"/login"，在这能改
//			.loginProcessingUrl("/authentication/form") 
//			.and()
//			.authorizeRequests() //下边的都是授权的配置
//			.antMatchers("/login.html").permitAll() //放过登录页不过滤，否则报错
//			.anyRequest()		//任何请求
//			.authenticated()	//都需要身份认证
//			.and()
//			.csrf().disable() //关闭csrf防护
//			;	
//	}
	
	//版本二：可配置的登录页
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//实现需要认证的接口跳转表单登录,安全=认证+授权
		//http.httpBasic() //这个就是默认的弹框认证
		http.formLogin() //表单认证
			.loginPage("/authentication/require") //处理用户认证BrowserSecurityController
			//登录过滤器UsernamePasswordAuthenticationFilter默认登录的url是"/login"，在这能改
			.loginProcessingUrl("/authentication/form") 
			.successHandler(imoocAuthenticationSuccessHandler)//自定义的认证后处理器
			.failureHandler(imoocAuthenticationFailureHandler) //登录失败后的处理
			.and()
			.authorizeRequests() //下边的都是授权的配置
			// /authentication/require：处理登录，securityProperties.getBrowser().getLoginPage():用户配置的登录页
			.antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll() //放过登录页不过滤，否则报错
			.anyRequest()		//任何请求
			.authenticated()	//都需要身份认证
			.and()
			.csrf().disable() //关闭csrf防护
			;	
	}
}

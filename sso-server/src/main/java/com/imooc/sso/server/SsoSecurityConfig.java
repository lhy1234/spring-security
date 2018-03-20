package com.imooc.sso.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * ClassName: SsoSecurityConfig 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月20日
 */
@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	//密码加密解密
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 配置登录方式等
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin() //表单登录
			.and()
			.authorizeRequests() //所有请求都需要认证
			.anyRequest()
			.authenticated();
	}
	
	/**
	 * 告诉AuthenticationManager ,使用自己的方式登录时 【查询用户】和密码加密器
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}

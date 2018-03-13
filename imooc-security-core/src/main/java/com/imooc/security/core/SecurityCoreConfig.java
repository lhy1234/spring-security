package com.imooc.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.imooc.security.core.properties.SecurityProperties;

/**
 * 使配置SecurityProperties生效
 * ClassName: SecurityCoreConfig 
 * @Description: 使配置SecurityProperties生效
 * @author lihaoyang
 * @date 2018年2月28日
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

		
	/**
	 * 注意是org.springframework.security.crypto.password.PasswordEncoder
	 * @Description: 密码的加密解密
	 * @param @return   
	 * @return PasswordEncoder  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年3月12日
	 */
		@Bean
		public PasswordEncoder passwordencoder(){
			//BCryptPasswordEncoder implements PasswordEncoder
			return new BCryptPasswordEncoder();
		}
		
}

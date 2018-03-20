package com.imooc.sso.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用1
 * ClassName: SsoCient1Application 
 * @Description: @EnableOAuth2Sso 开启sso
 * @author lihaoyang
 * @date 2018年3月16日
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SsoClient1Application {

	@GetMapping("/user")
	public Authentication  user(Authentication  user){
		return user;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SsoClient1Application.class, args);
	}
}

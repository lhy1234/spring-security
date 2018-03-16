package com.imooc.sso.client;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * ClassName: SsoCient1Application 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月16日
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SsoClient1Application {

	@GetMapping("/user")
	public Authorization user(Authorization user){
		return user;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SsoClient1Application.class, args);
	}
}

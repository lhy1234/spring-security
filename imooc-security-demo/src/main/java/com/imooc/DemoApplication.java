 package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * ClassName: DemoApplication 
 * @Description: demo
 * @author lihaoyang
 * @date 2018年2月23日
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	@GetMapping("/hello")
	public String hello(){
		return "Hello Spring Security！";  
	}

}

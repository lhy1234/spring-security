package com.imooc.security.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 认证服务器
 * ClassName: ImoocAuthenticationServerConfig 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月12日
 */
@Configuration
@EnableAuthorizationServer //这个注解就是实现了一个认证服务器
public class ImoocAuthenticationServerConfig {

}

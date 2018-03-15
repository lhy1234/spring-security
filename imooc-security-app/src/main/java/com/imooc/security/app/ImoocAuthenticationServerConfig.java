package com.imooc.security.app;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.imooc.security.core.properties.OAuth2ClientProperties;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * 认证服务器
 * ClassName: ImoocAuthenticationServerConfig 
 * @Description: 
 * extends AuthorizationServerConfigurerAdapter 自定义token生成
 * @author lihaoyang
 * @date 2018年3月12日
 */
@Configuration
@EnableAuthorizationServer //这个注解就是实现了一个认证服务器
public class ImoocAuthenticationServerConfig extends AuthorizationServerConfigurerAdapter{

	/*
	 * 不继承AuthorizationServerConfigurerAdapter，这些bean会自己找，配了，就要自己实现 
	 */
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private  UserDetailsService userDetailsService;
	
	//配置文件
	@Autowired
	private SecurityProperties securityProperties;
	
	//token存在redis，默认是在内存
	@Autowired
	private TokenStore tokenStore;
	
	/**
	 * 配置TokenEndpoint 是  /oauth/token处理的入口点
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				 .authenticationManager(authenticationManager)
				 .userDetailsService(userDetailsService);
	}
	
	/**
	 * 功能：认证服务器会给哪些第三方应用发令牌
	 * 	   覆盖了该方法，application.properties里配置的
	 * 				security.oauth2.client.clientId = imooc
	 *				security.oauth2.client.clientSecret = imoocsecret
	 *	 就失效了
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//1，写死
//		clients.jdbc(dataSource)就是qq场景用的，有第三方公司注册过来，目前场景是给自己的应用提供接口，所以用内存就行
//		clients.inMemory()
//				//~========================== 在这里配置和写配置文件一样================
//				.withClient("imooc") //第三方应用用户名
//				.secret("imoocsecret") //密码                       
//				.accessTokenValiditySeconds(7200)//token有效期
//				.authorizedGrantTypes("password","refresh_token") //支持的授权模式
//				.scopes("all","read","write") //相当于oauth的权限，这里配置了，请求里的必须和这里匹配
//				//~=======如果有多个client，这里继续配置
//				.and()
//				.withClient("xxxxx"); 
		
		//2，读取配置文件
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		//判断是否配置了客户端
		if(ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())){
			for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
				builder.withClient(config.getClientId())
						.secret(config.getClientSecret())
						.accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
						.authorizedGrantTypes("password","refresh_token") //这些也可以配置也可以写死，看心情
						.scopes("all","read","write"); 
			}
		}
		
	}
}

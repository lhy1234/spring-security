package com.imooc.sso.server;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * 认证服务器
 * ClassName: AuthorizationServerConfig 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月16日
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("imooc1")
			.secret("imoocsecrect1")
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes("all")
			.and()
			.withClient("imooc2")
			.secret("imoocsecrect2")
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes("all");
	}
	
	
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	/**
	 * 给JWT加签名
	 * @Description: 给JWT加签名
	 * @param @return   
	 * @return JwtAccessTokenConverter  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年3月16日
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(){
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("imooc");
        return converter;
	}

	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//其他应用要访问认证服务器的tokenKey（就是下边jwt签名的imooc）的时候需要经过身份认证，获取到秘钥才能解析jwt
		security.tokenKeyAccess("isAuthenticated()");
	}
	
	
}

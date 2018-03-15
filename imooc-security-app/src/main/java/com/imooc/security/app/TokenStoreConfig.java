package com.imooc.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * token存储到redis,默认是在内存不行
 * ClassName: TokenStoreConfig 
 * @Description:  token存储策略
 * @author lihaoyang
 * @date 2018年3月15日
 */
@Configuration
public class TokenStoreConfig {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	public TokenStore redisTokenStore(){
		return new RedisTokenStore(redisConnectionFactory);
	}
	
}

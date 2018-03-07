package com.imooc.security.core.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * SpringSocial相关配置
 * ClassName: SocialConfig 
 * @Description: 社交相关配置
 * @author lihaoyang
 * @date 2018年3月7日
 */
@Configuration	
@EnableSocial //把Spring Social相关的特性启动
public class SocialConfig extends SocialConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	
	/**
	 * 配置JdbcUsersConnectionRepository
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
	
		/**
		 * 参数：
		 *  1, dataSource：数据源 注进来
		 *  2，connectionFactoryLocator：
		 *  	根据条件去查找需要的 ConnectionFactory，因为系统里可能有多个ConnectionFactory，如qq，微信等，使用默认穿的
		 *  3，textEncryptor：把插入到数据库的数据加密解密
		 */
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());//先不加密
		repository.setTablePrefix("imooc_"); //表前缀
		return repository;
	}
	
	
	@Bean
	public SpringSocialConfigurer imoocSocialSecurityConfig(){
		return new SpringSocialConfigurer();
	}
}

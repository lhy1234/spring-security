package com.imooc.security.core.authentication.authorize;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 有了这个就不用在配置类里配了
 * ClassName: ImoocAuthorizeConfigManager 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月20日
 */
//@Component
public class ImoocAuthorizeConfigManager implements AuthorizeConfigManager {

	@Autowired
	private Set<AuthorizeConfigProvider> authorizeConfigProviders;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		for(AuthorizeConfigProvider authorizeConfigProvider:authorizeConfigProviders){
			authorizeConfigProvider.config(config);
		}
		config.anyRequest().authenticated();
	}

}

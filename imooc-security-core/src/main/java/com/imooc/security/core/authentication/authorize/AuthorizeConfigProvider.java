package com.imooc.security.core.authentication.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 权限配置
 * ClassName: AuthorizeConfigProvider 
 * @Description: 配置权限过滤的url等
 * @author lihaoyang
 * @date 2018年3月20日
 */
public interface AuthorizeConfigProvider {

	void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}

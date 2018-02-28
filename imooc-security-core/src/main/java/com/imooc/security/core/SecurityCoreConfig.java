package com.imooc.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;

/**
 * 使配置SecurityProperties生效
 * ClassName: SecurityCoreConfig 
 * @Description: 使配置SecurityProperties生效
 * @author lihaoyang
 * @date 2018年2月28日
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}

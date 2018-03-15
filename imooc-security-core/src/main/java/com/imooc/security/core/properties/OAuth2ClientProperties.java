package com.imooc.security.core.properties;

/**
 * 接口授权客户端配置 ClassName: OAuth2ClientProperties
 * 
 * @Description: 接口授权客户端配置
 * @author lihaoyang
 * @date 2018年3月15日
 */
public class OAuth2ClientProperties {

	private String clientId;

	private String clientSecret;

	private int accessTokenValiditySeconds = 3600; //没配置就用默认值
	
	// xxxxx在这里扩展配置

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public int getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

}

package com.imooc.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * AuthenticationManager 认证时候需要用的一个Provider
 * ClassName: SmsCodeAuthenticationProvider 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月8日
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	
	/**
	 * 认证
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//能进到这说明authentication是SmsCodeAuthenticationToken，转一下
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken)authentication;
		//token.getPrincipal()就是手机号 mobile
		UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
		
		//认证没通过
		if(user == null){
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		//认证通过
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		//把认证之前得token里存的用户信息赋值给认证后的token对象
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	/**
	 * 告诉AuthenticationManager，如果是SmsCodeAuthenticationToken的话用这个类处理
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		//判断传进来的authentication是不是SmsCodeAuthenticationToken类型的
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}

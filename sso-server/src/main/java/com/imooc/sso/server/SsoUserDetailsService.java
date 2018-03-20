package com.imooc.sso.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 配置自己的登录，findByUsername而不是spring默认的user
 * ClassName: SsoUserDetailsService 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月20日
 */
@Component
public class SsoUserDetailsService implements UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
					                  								  
		return new User(username,  // 用户名 
						passwordEncoder.encode("123456") , //密码    
						AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));//权限集合
		
	}

}

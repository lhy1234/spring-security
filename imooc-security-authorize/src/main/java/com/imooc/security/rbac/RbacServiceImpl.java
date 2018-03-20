package com.imooc.security.rbac;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		
		Object principal = authentication.getPrincipal();
		
		boolean hasPermission = false;
		
		if(principal instanceof UserDetails){
			
			String username = ((UserDetails)principal).getUsername();
			
			//读取用户所有权限的url，需要查询数据库
			Set<String> urls = new HashSet<>();
			urls.add("/user/*");
			
			for(String url : urls){
				if(antPathMatcher.match(url, request.getRequestURI())){
					hasPermission = true ;
					break ;
				}
			}
		}
		return hasPermission;
	}

}

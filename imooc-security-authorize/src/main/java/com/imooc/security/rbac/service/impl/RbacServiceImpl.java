package com.imooc.security.rbac.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.imooc.security.rbac.service.RbacService;
import com.imooc.security.rbac.service.UserService;
 
@Component("rbacService")
public class RbacServiceImpl implements RbacService { 
	
	@Autowired
	private UserService userService;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		
		Object principal = authentication.getPrincipal();
		
		boolean hasPermission = false;
		
		if(principal instanceof UserDetails){
			
			String username = ((UserDetails)principal).getUsername();
			//查询用户所有权限urls
			Set<String> permissions = userService.findAllPermissionsUrls(username);
//			//读取用户所有权限的url，需要查询数据库
//			Set<String> urls = new HashSet<>();
//			urls.add("/user/*");
			
			for(String url : permissions){
				System.err.println(">>>请求的url："+request.getRequestURI()+"-----匹配的权限："+url);
				if(antPathMatcher.match(url==null?"":url, request.getRequestURI())){
					hasPermission = true ;
					break ;
				}
			}
		}
		return hasPermission;
	}

}

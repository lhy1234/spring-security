package com.imooc.security.rbac;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface RbacService {

	public boolean hasPermission(HttpServletRequest request ,Authentication authentication);
}

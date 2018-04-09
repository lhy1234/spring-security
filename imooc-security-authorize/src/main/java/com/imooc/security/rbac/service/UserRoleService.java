package com.imooc.security.rbac.service;

import java.util.Set;

public interface UserRoleService {

	/**
	 * 查询用户所有的角色ids
	 * @param userId 用户id
	 * @return
	 */
	public Set<Long> findUserRoleIdsByUserId(Long userId);
	
	
	
}

package com.imooc.security.rbac.service;

import java.util.Set;

public interface RolePermissionService {

	/**
	 * 查询一个角色对应的权限ids
	 * @param roleId
	 * @return
	 */
	public Set<Long> findRolePermissionIds(Long roleId);
	
	
}

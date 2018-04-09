package com.imooc.security.rbac.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.security.rbac.domain.RolePermission;
import com.imooc.security.rbac.mapper.RolePermissionMapper;
import com.imooc.security.rbac.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService{

	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	@Override
	public Set<Long> findRolePermissionIds(Long roleId) {
		
		Set<Long> permissionIds = new HashSet<Long>();
		
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleId(roleId);
		List<RolePermission> list = rolePermissionMapper.select(rolePermission);
		
		if(list != null && list.size()>0){
			for(RolePermission link : list){
				permissionIds.add(link.getPermissionId());
			}
		}
		return permissionIds;
	}

}

package com.imooc.security.rbac.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.imooc.security.rbac.domain.UserRole;
import com.imooc.security.rbac.mapper.UserRoleMapper;
import com.imooc.security.rbac.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	@Override
	public Set<Long> findUserRoleIdsByUserId(Long userId) {
		Set<Long> roleIds = new HashSet<Long>();
		
		Example example = new Example(UserRole.class);  
		example.createCriteria().andEqualTo("userId", userId);
		List<UserRole> list = userRoleMapper.selectByExample(example);
		
		if(list != null && list.size()>0){
			for(UserRole link : list){
				roleIds.add(link.getRoleId());
			}
		}
		return roleIds;
	}

}

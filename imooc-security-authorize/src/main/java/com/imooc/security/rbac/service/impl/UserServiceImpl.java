package com.imooc.security.rbac.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.imooc.security.rbac.domain.Permission;
import com.imooc.security.rbac.domain.Role;
import com.imooc.security.rbac.domain.User;
import com.imooc.security.rbac.mapper.UserMapper;
import com.imooc.security.rbac.service.PermissionService;
import com.imooc.security.rbac.service.RolePermissionService;
import com.imooc.security.rbac.service.RoleService;
import com.imooc.security.rbac.service.UserRoleService;
import com.imooc.security.rbac.service.UserService;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	
	@Override
	public int addUser(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public int deleteById(Long userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User findById(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public User findByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		return userMapper.selectOne(user);
	}

	@Override
	public Set<String> findAllPermissions(Long userId) {
		//用户所有的权限ids
		Set<Long> permissionIds = findAllPermissionIds(userId);
		//根据权限ids查询所有的权限标识符
		return permissionService.findPermissionsCodeByIds(permissionIds);
	}


	@Override
	public Set<Long> findAllPermissionIds(Long userId) {
		Set<Long> permissionIds = new HashSet<Long>();
		//查询用户所有角色ids
		Set<Long> userRoleIds = userRoleService.findUserRoleIdsByUserId(userId);
		if(userRoleIds.size()>0){
			for(Long roleId :userRoleIds){
				//查询每个角色对应的权限ids
				Set<Long> rolePermissionIds = rolePermissionService.findRolePermissionIds(roleId);
				if(rolePermissionIds.size()>0){
					for(Long perId :rolePermissionIds){
						permissionIds.add(perId);
					}
				}
			}
		}
		return permissionIds;
	}

	@Override
	public Set<Long> findAllPermissionIds(String username) {
		Set<Long> permissionIds = new HashSet<Long>();
		User user = new User();
		user.setUsername(username);
		User result = userMapper.selectOne(user);
		//查询用户所有角色ids
		Set<Long> userRoleIds = userRoleService.findUserRoleIdsByUserId(result.getId());
		if(userRoleIds.size()>0){
			for(Long roleId :userRoleIds){
				//查询每个角色对应的权限ids
				Set<Long> rolePermissionIds = rolePermissionService.findRolePermissionIds(roleId);
				if(rolePermissionIds.size()>0){
					for(Long perId :rolePermissionIds){
						permissionIds.add(perId);
					}
				}
			}
		}
		return permissionIds;
	}

	@Override
	public Set<String> findAllPermissions(String username) {
		//用户所有的权限ids
		Set<Long> permissionIds = findAllPermissionIds(username);
		//根据权限ids查询所有的权限标识符
		return permissionService.findPermissionsCodeByIds(permissionIds);
	}

	@Override
	public Set<String> findAllPermissionsUrls(String username) {
		Set<String> urls = new HashSet<>();
		//用户所有的权限ids
		Set<Long> permissionIds = findAllPermissionIds(username);
		//根据权限ids查询所有的权限标识符
		List<Permission> permissions = permissionService.findPermissionsByIds(permissionIds);
		if(permissionIds != null && permissionIds.size() > 0){
			for (Permission permission : permissions) {
				urls.add(permission.getUrl());
			}
		}
		return urls;
	}

	@Override
	public List<Role> findAllRolesByUserId(Long userId) {
		List<Role> roles = new ArrayList<Role>();
		Set<Long> roleIds = userRoleService.findUserRoleIdsByUserId(userId);
		if(roleIds != null && roleIds.size()>0){
			for (Long roleId : roleIds) {
				roles.add(roleService.findById(roleId));
			}
		}
		return roles;
	}

}

package com.imooc.security.rbac.service;

import java.util.List;
import java.util.Set;

import com.imooc.security.rbac.domain.Role;
import com.imooc.security.rbac.domain.User;



public interface UserService {

	public int addUser(User user);
	
	public int deleteById(Long userId);
	
	public int updateUser(User user);
	
	public User findById(Long userId);
	
	
	public User findByUsername(String username);
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	Set<String> findAllPermissions(Long userId);
	
	/**
	 * 查询用户的所有权限表ID
	 * @param userId 用户ID
	 * @return 
	 */
	Set<Long> findAllPermissionIds(Long userId);
	
	/**
	 * 查询用户的所有权限表ID
	 * @param username 用户名
	 * @return 
	 */
	Set<Long> findAllPermissionIds(String username);
	
	/**
	 * 查询用户的所有权限
	 * @param username  用户名
	 */
	Set<String> findAllPermissions(String username);
	
	
	/**
	 * 查询用户的所有权限url
	 * @param username  用户名
	 */
	Set<String> findAllPermissionsUrls(String username);
	
	/**
	 * 根据用户id查询所有的角色
	 * @Description: 根据用户名查询所有的角色
	 * @param @param userId 用户id
	 * @param @return   
	 * @return List<Role>  拥有的角色
	 * @throws
	 * @author lihaoyang
	 * @date 2018年4月9日
	 */
	List<Role> findAllRolesByUserId(Long userId);
	
}

package com.imooc.security.rbac.service;

import java.util.List;
import java.util.Set;

import com.imooc.security.rbac.domain.Permission;
import com.imooc.security.rbac.vo.EUTreeNode;


public interface PermissionService {
	/**
	 * 通过id查询权限实体
	 * @param permissionId
	 * @return
	 */
	public Permission findById(Long permissionId);
	/**
	 * 通过权限id查询权限实体列表
	 * @param permissionIds
	 * @return
	 */
	public List<Permission> findPermissionsByIds(Set<Long> permissionIds);
	
	/**
	 * 通过权限id查询权限 标识符列表
	 * @param permissionIds
	 * @return
	 */
	public Set<String> findPermissionsCodeByIds(Set<Long> permissionIds);
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<Permission> findPermissinosByParentId(Long parentId, Set<Long> permissionIds);
	
	/**
	 * 获取用户菜单列表
	 */
	List<Permission> getUserMenuList(Long userId);
	
	/**
	 * 查询所有权限
	 * @return
	 */
//	List<Permission> findAllPermissions();
	
	/**
	 * 递归 查询 权限树
	 * @param permissionList 要递归的权限列表
	 * @param permissionId 权限id
	 * @return
	 */
	List<Permission> findPermissionsTree(List<Permission> permissionList,Long permissionId);
	
	/**
	 * 得到easyui tree node 类型的权限树
	 * @return easyui tree node
	 */
	List<EUTreeNode> getEUTreeNodePermissions();
	/**
	 * 查询所有type是按钮的权限
	 * @return
	 */
	List<Permission> findAllMenuPermission();
	
	
}

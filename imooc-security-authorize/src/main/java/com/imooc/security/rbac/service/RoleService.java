package com.imooc.security.rbac.service;

import java.util.List;

import com.imooc.security.rbac.domain.Role;
import com.imooc.security.rbac.vo.EUDataGridResult;


public interface RoleService {
	
	public int addRole(Role role) throws Exception;
	
	/**
	 * 添加角色，同时授权
	 * @param role 角色
	 * @param permissionIds 角色授权的权限ids，逗号分隔
	 * @return
	 * @throws Exception
	 */
	public int addRoleWithPermissions(Role role,String permissionIds) throws Exception;
	
	public int deleteById(Long roleId);
	
	/**
	 * 查询用户的所有有角色ids
	 * @param userId 用户id
	 * @return
	 */
	public List<Long> findUserRoleIds(Long userId);

	/**
	 * 查询所有的角色列表
	 * @param page
	 * @param rows
	 * @return
	 */
	public EUDataGridResult findAllRoleList(Integer page, Integer rows);

	/**
	 * 通过id查询角色
	 * @Description: 通过id查询角色
	 * @param @return   
	 * @return Role  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年4月9日
	 */
	public Role findById(Long id);

}

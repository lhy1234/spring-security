package com.imooc.security.rbac.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.imooc.security.rbac.domain.Permission;
import com.imooc.security.rbac.mapper.PermissionMapper;
import com.imooc.security.rbac.mapper.RolePermissionMapper;
import com.imooc.security.rbac.mapper.UserRoleMapper;
import com.imooc.security.rbac.service.PermissionService;
import com.imooc.security.rbac.service.UserService;
import com.imooc.security.rbac.vo.EUTreeNode;



@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	@Autowired
	private UserService userService;
	

	@Override
	public Permission findById(Long permissionId) {
		return permissionMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public List<Permission> findPermissionsByIds(Set<Long> permissionIds) {
		List<Permission> permissions = new ArrayList<Permission>();
		for(Long perId :permissionIds){
			permissions.add(this.findById(perId));
		}
		return permissions;
	}

	@Override
	public Set<String> findPermissionsCodeByIds(Set<Long> permissionIds) {
		Set<String> permissionsCode = new HashSet<String>();
		List<Permission> permissions = findPermissionsByIds(permissionIds);
		if(permissions != null && permissions.size() >0){
			for(Permission per :permissions){
				if(StringUtils.isNotBlank(per.getPermissionCode())){
					permissionsCode.add(per.getPermissionCode());
				}
				
			}
		}
		return permissionsCode;
	}

	@Override 
	public List<Permission> findPermissinosByParentId(Long parentId,
			Set<Long> permissionIds) {
		//根据父id查询所有儿子，頂級是0
	
		Example example = new Example(Permission.class);
		example.createCriteria().andEqualTo("parentId", parentId);
		
		List<Permission> children = permissionMapper.selectByExample(example);
		
		if(children == null){
			return children;
		}
		//
		List<Permission> userMenuList = new ArrayList<Permission>();
		for(Permission  per : children){
			//用户的权限ids是否包含一级菜单
			if(permissionIds.contains(per.getId())){
				userMenuList.add(per);
			}
		}
		return userMenuList;
	}

	@Override
	public List<Permission> getUserMenuList(Long userId) {
		//查詢用户所有的權限ids列表
		Set<Long> permissionIds = userService.findAllPermissionIds(userId);
		//根据用户所有的权限ids 查询用户的菜单列表
		List<Permission> menuList = getAllMenuList(permissionIds);
		return menuList;
	}
	/**
	 * 获取所有菜单列表
	 */
	private List<Permission> getAllMenuList(Set<Long> menuIdList){
		//0L标识一级菜单，根据用户拥有的权限表的ids，过滤用户得到用户的一级菜单
		List<Permission> menuList = findPermissinosByParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}
	
	/**
	 * 递归得到用户所有权限的子权限
	 * @param menuList 用户所有的顶级权限列表
	 * @param menuIdList 用户所有的权限id
	 * @return
	 */
	private List<Permission> getMenuTreeList(List<Permission> permissionList, Set<Long> permissionIdList){
		List<Permission> treePermissionList = new ArrayList<Permission>();
		
		for(Permission permission : permissionList){
			if(permission.getParentId() == 0){//不是叶子节点
//				permission.setList(getMenuTreeList(findPermissinosByParentId(entity.getId(), menuIdList), menuIdList));
				//----递归
				//查询该权限所有子权限
				List<Permission> childrenPer = findPermissinosByParentId(permission.getId(),permissionIdList);
				permission.setChildren(getMenuTreeList(childrenPer,permissionIdList));
			}
			treePermissionList.add(permission);
		}
		
		return treePermissionList;
	}


	@Override
	public List<Permission> findAllMenuPermission() {
		//创建查询条件
		Example example = new Example(Permission.class);
		example.createCriteria().andEqualTo("type", "menu");	//类型是菜单的权限

		List<Permission> menuList = permissionMapper.selectByExample(example);
		return menuList;
	}

	@Override
	public List<Permission> findPermissionsTree(
			List<Permission> permissionList, Long permissionId) {
		
		List<Permission> treePermissionList = new ArrayList<Permission>();
		
		Example example = new Example(Permission.class);
		example.createCriteria().andEqualTo("parentId", permissionId);
		List<Permission> topLevelPermissions  = permissionMapper.selectByExample(example);
		
		for (Permission permission : topLevelPermissions) {
			if(permission.getIsLeaf()==0){
				List<Permission> children = findPermissionsTree(permissionList,permission.getId());
				permission.setChildren(children);
			}
			treePermissionList.add(permission);
		}
		return treePermissionList;
	}

	@Override
	public List<EUTreeNode> getEUTreeNodePermissions() {
		//查询权限树结构
		List<Permission> permissionsTree = findPermissionsTree(new ArrayList<Permission>(),0L);
		//转换
		return list2EUTreeNodeList(permissionsTree);
	}

	/**
	 * javalist转easyui treeNode list
	 * @param permissions
	 * @return
	 */
	private List<EUTreeNode> list2EUTreeNodeList(List<Permission> permissionList){
		List<EUTreeNode> result = new ArrayList<EUTreeNode>();
		if(permissionList != null && permissionList.size()>0){
			for (Permission permission : permissionList) {
				EUTreeNode node = new EUTreeNode();
				node.setId(permission.getId());
				node.setText(permission.getName());
				node.setState("open");
				if(permission.getChildren().size()>0){
					//有子节点，递归
					node.setChildren(list2EUTreeNodeList(permission.getChildren()));
				}
				result.add(node);
			}
		}
		return result;
	}

	/*@Override
	public List<Permission> findAllPermissions() {
		//创建查询条件
		PermissionExample example = new PermissionExample();
		List<Permission> permissions = permissionMapper.selectByExample(example);
		return permissions;
	}*/
}

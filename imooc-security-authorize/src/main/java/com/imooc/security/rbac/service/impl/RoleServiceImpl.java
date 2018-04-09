package com.imooc.security.rbac.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.security.rbac.domain.Role;
import com.imooc.security.rbac.mapper.RoleMapper;
import com.imooc.security.rbac.service.RoleService;
import com.imooc.security.rbac.vo.EUDataGridResult;

@Service
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public int addRole(Role role) throws Exception{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Long roleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Long> findUserRoleIds(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EUDataGridResult findAllRoleList(Integer page, Integer rows) {
		//查询角色列表
		//分页处理  
		PageHelper.startPage(page, rows);   
		List<Role> list = roleMapper.select(new Role());
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public int addRoleWithPermissions(Role role, String permissionIds)
			throws Exception {
		//补全角色
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		//插入
		this.roleMapper.insert(role);
		//添加角色对应的
		System.out.println("role id "+role.getId());
		return 0;
	}

	@Override
	public Role findById(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

}

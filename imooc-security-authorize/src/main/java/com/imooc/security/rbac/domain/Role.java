package com.imooc.security.rbac.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;

@Table(name="tb_role")
public class Role implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据库表主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 角色名称
	 */
    private String name;

    private String description;

    private Integer available;
    
    /**
	 * 角色拥有权限的资源集合
	 */
	@Transient
	private Set<RolePermission> permissions  = new HashSet<>();
	
	/**
	 * 角色的用户集合
	 */
	@Transient
	private Set<UserRole> users = new HashSet<>();

   
    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public Set<RolePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<RolePermission> permissions) {
		this.permissions = permissions;
	}

	public Set<UserRole> getUsers() {
		return users;
	}

	public void setUsers(Set<UserRole> users) {
		this.users = users;
	}
    
    
}
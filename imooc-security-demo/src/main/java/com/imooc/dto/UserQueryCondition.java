package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * ClassName: UserQueryCondition 
 * @Description:  查询条件
 * @author lihaoyang
 * @date 2018年2月24日
 */
public class UserQueryCondition {

	private String username;
	
	@ApiModelProperty(value="年龄起始值")
	private String age;
	@ApiModelProperty(value="年龄终止值")
	private String ageTo;
	
	private String other;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(String ageTo) {
		this.ageTo = ageTo;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "UserQueryCondition [username=" + username + ", age=" + age + ", ageTo=" + ageTo + ", other=" + other
				+ "]";
	}
	
	
}

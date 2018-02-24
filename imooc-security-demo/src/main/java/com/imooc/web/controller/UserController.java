package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;

@RestController
@RequestMapping("/user")
public class UserController {
	
	/**
	 * @Description: 条件查询
	 * @param @param condition
	 * @param @param pageable
	 * @param @return   
	 * @return List<User>  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年2月24日
	 */
	@GetMapping("query")
	@JsonView(User.UserSimpleView.class)
	public List<User> query(
			//@RequestParam(value="username",required=false,defaultValue="lhy") String username
			UserQueryCondition condition , Pageable pageable){
//		System.err.println(username);
		System.err.println(condition.toString());
		System.err.println(pageable.toString());
		
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	/**
	 * 详情
	 * @Description: TODO
	 * @param @param id
	 * @param @return   
	 * @return User  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年2月24日
	 */
	@GetMapping("detail/{id:\\d+}") //{}里可以是正则，匹配数字
//	@GetMapping("detail/{id}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable(value="id",required=true) String id){
		System.err.println(id);
		User user = new User();
		user.setUsername("tom");
		return user;
	}

}

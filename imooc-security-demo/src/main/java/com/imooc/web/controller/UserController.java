package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;

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
	@GetMapping
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
	@GetMapping("{id:\\d+}") //{}里可以是正则，匹配数字
//	@GetMapping("detail/{id}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable(value="id",required=true) String id){
		
//		throw new RuntimeException("query deltail interface has error!");
//		throw new UserNotExistException(id);
		System.err.println(">>>>>>进入User Controller -->  getInfo 方法");
		System.err.println(id);
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123456");
		user.setId("1");
		return user;
	}
	
	/**
	 * 创建
	 * @Description: 
	 * //@RequestBody:json映射到java
	 * 	@Valid 和User类上的@NotBlank注解一起做校验
	 *  BindingResult存储的是校验错误信息
	 * @param @param user
	 * @param @return   
	 * @return User  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年2月24日
	 */
	@PostMapping
	public User create(@Valid @RequestBody User user){ //,BindingResult errors
		
//		if(errors.hasErrors()){
//			errors.getAllErrors().stream()
//			.forEach(error -> System.err.println(error.getDefaultMessage()));
//		}
		
		user.setId("1");
		System.err.println(user);
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors){

		if(errors.hasErrors()){
			errors.getAllErrors().stream()
			.forEach(error -> System.err.println(error.getDefaultMessage()));
		}
		System.err.println(user);
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id){
		System.err.println("delete method id is >>>>>>>"+id);
	}

}

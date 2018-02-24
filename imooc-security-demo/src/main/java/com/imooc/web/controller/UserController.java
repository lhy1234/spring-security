package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@GetMapping
	public List<User> query(
			//@RequestParam(value="username",required=false,defaultValue="lhy") String username
			UserQueryCondition condition , Pageable pageable
			){
		
//		System.err.println(username);
		
		System.err.println(condition.toString());
		
		System.err.println(pageable.toString());
		
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		
		return users;
	}

}

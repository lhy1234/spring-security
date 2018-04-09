package com.imooc.security;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import com.imooc.security.rbac.domain.Role;
import com.imooc.security.rbac.domain.User;
import com.imooc.security.rbac.service.RoleService;
import com.imooc.security.rbac.service.UserService;

/**
 * UserDetailsService是SpringSecurity的一个接口，
 * 只有一个方法：根据用户名获取用户详情
 * ClassName: MyUserDetailService 
 * @Description: 
 * 	SocialUserDetailsService:SpringSocial查询用户信息的接口
 * @author lihaoyang
 * @date 2018年2月28日
 */
@Component
public class MyUserDetailsService implements UserDetailsService,SocialUserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * UserDetails接口，实际可以自己实现这个接口，返回自己的实现类
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登录用户名："+username);
		//根据用户名查询用户信息
		
		//User：springsecurity 对 UserDetails的一个实现
		//为了演示在这里用passwordEncoder加密一下密码，实际中在注册时就加密，此处直接拿出密码
//		String password = passwordEncoder.encode("123456");
//		System.err.println("加密后密码：  "+password);
//		//参数：用户名|密码|是否启用|账户是否过期|密码是否过期|账户是否锁定|权限集合
//		return new User(username,password,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		
		return buildUserDetail(username);
	}

	
	/**
	 * 第三方登录使用
	 */
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("设计用户id："+userId);
		return buildUser(userId);
	}



	private SocialUserDetails buildUser(String userId) {
		String password = passwordEncoder.encode("123456");
		
		com.imooc.security.rbac.domain.User user = userService.findByUsername(userId);
		
		System.err.println("加密后密码：  "+password);
		//参数：用户名|密码|是否启用|账户是否过期|密码是否过期|账户是否锁定|权限集合
		return new SocialUser(userId,password,true,true,true,true,
				//工具类 将字符串转换为权限集合，ROLE_角色 是spring要求的权限格式
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
	}
	
	
	
	private SocialUser buildUserDetail(String username) {
		String password = passwordEncoder.encode("123456");
		//根据用户名查询用户
		User user = userService.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名或密码错误！");
		}
		//查询角色集合
		Set<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);  
		
		 //封装成spring security的user  
		//参数：用户名|密码|是否启用|账户是否过期|密码是否过期|账户是否锁定|权限集合
		SocialUser userdetail = new SocialUser(user.getUsername(), user.getPassword(),  
        true, // 账号状态 0 表示停用 1表示启用  
        true, true, true, grantedAuths // 用户的权限  
        );  
    
		
		
		
		System.err.println("加密后密码：  "+password);
		
	    return userdetail;  
		//参数：用户名|密码|是否启用|账户是否过期|密码是否过期|账户是否锁定|权限集合
//		return new User(username,password,true,true,true,true,
//				//工具类 将字符串转换为权限集合，ROLE_角色 是spring要求的权限格式
//				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
	}
	
	
	
	//取得用户的权限  
    private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {  
        Set<GrantedAuthority> authSet = new HashSet<>();  
        List<Role> roles = userService.findAllRolesByUserId(user.getId());
        if(roles.size() > 0){  
            for(Role role : roles) {
            	logger.info(user.getUsername()+" 拥有角色 : "+role);
                authSet.add(new SimpleGrantedAuthority(role.getName().trim()));  
            }  
        }  
        return authSet;  
    }  

}

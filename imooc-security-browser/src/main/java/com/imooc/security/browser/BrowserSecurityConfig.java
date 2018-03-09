package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import com.imooc.security.browser.session.ImoocExpiredSessionStrategy;
import com.imooc.security.browser.session.ImoocExpiredSessionStrategy2;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.SmsCodeFilter;
import com.imooc.security.core.validate.code.ValidateCodeFilter;

@Configuration //这是一个配置
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//读取用户配置的登录页配置
	@Autowired
	private SecurityProperties securityProperties;
	
	//自定义的登录成功后的处理器
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	//自定义的认证失败后的处理器
	@Autowired
	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	//数据源
	@Autowired
	private DataSource dataSource;
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
	
	
	@Autowired
	private SpringSocialConfigurer imoocSocialSecurityConfig;

	//注意是org.springframework.security.crypto.password.PasswordEncoder
	@Bean
	public PasswordEncoder passwordencoder(){
		//BCryptPasswordEncoder implements PasswordEncoder
		return new BCryptPasswordEncoder();
	}
	
	
	/**
	 * 记住我TokenRepository配置，在登录成功后执行
	 * 登录成功后往数据库存token的
	 * @Description: 记住我TokenRepository配置
	 * @param @return   JdbcTokenRepositoryImpl
	 * @return PersistentTokenRepository  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年3月5日
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		//启动时自动生成相应表，可以在JdbcTokenRepositoryImpl里自己执行CREATE_TABLE_SQL脚本生成表
		//第二次启动表已存在，需要注释
//		jdbcTokenRepository.setCreateTableOnStartup(true);
		return jdbcTokenRepository;
	}
	
	
	//版本一：配置死的登录页
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		//实现需要认证的接口跳转表单登录,安全=认证+授权
//		//http.httpBasic() //这个就是默认的弹框认证
//		http.formLogin() //表单认证
//			.loginPage("/login.html") //登录页面
//			//登录过滤器UsernamePasswordAuthenticationFilter默认登录的url是"/login"，在这能改
//			.loginProcessingUrl("/authentication/form") 
//			.and()
//			.authorizeRequests() //下边的都是授权的配置
//			.antMatchers("/login.html").permitAll() //放过登录页不过滤，否则报错
//			.anyRequest()		//任何请求
//			.authenticated()	//都需要身份认证
//			.and()
//			.csrf().disable() //关闭csrf防护
//			;	
//	}
	
	//版本二：可配置的登录页
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//~~~-------------> 图片验证码过滤器 <------------------
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		//验证码过滤器中使用自己的错误处理
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		//配置的验证码过滤url
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		
		//~~~-------------> 短信验证码过滤器 <------------------
		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
		//验证码过滤器中使用自己的错误处理
		smsCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		//配置的验证码过滤url
		smsCodeFilter.setSecurityProperties(securityProperties);
		smsCodeFilter.afterPropertiesSet();
		
		
		
		//实现需要认证的接口跳转表单登录,安全=认证+授权
		//http.httpBasic() //这个就是默认的弹框认证
		//
		http 
			.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//			.apply(imoocSocialSecurityConfig)//社交登录
//			.and()
			//把验证码过滤器加载登录过滤器前边
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			//----------表单认证相关配置---------------
			.formLogin() 
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) //处理用户认证BrowserSecurityController
				//登录过滤器UsernamePasswordAuthenticationFilter默认登录的url是"/login"，在这能改
				.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) 
				.successHandler(imoocAuthenticationSuccessHandler)//自定义的认证后处理器
				.failureHandler(imoocAuthenticationFailureHandler) //登录失败后的处理
				.and() 
			//------------记住我相关配置	-------------
			.rememberMe()
				.tokenRepository(persistentTokenRepository())//TokenRepository，登录成功后往数据库存token的
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())//记住我秒数
				.userDetailsService(userDetailsService) //记住我成功后，调用userDetailsService查询用户信息
			.and()//-----------session相关配置---------------
			.sessionManagement()
				//++++++=基本这样配置++++++++
//				.invalidSessionUrl(SecurityConstants.SESSION_INVALID_PAGE) //session失效跳转地址，如果简单的处理只要这一个就够了
//				.maximumSessions(1) //一个用户只能登录一次，踢出前边登录用户
//				.expiredSessionStrategy(new ImoocExpiredSessionStrategy2()) //简洁版session失效策略
//				.maxSessionsPreventsLogin(true) //阻止并发登录
				
//				
				//++++++++重构后+++++++
				.invalidSessionStrategy(invalidSessionStrategy)
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())//阻止在登录
				.expiredSessionStrategy(sessionInformationExpiredStrategy) //session失效策略
			.and() //?俩and为啥呢
			.and()
			//-----------授权相关的配置 ---------------------
			.authorizeRequests()  
				// /authentication/require：处理登录，securityProperties.getBrowser().getLoginPage():用户配置的登录页
				.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL, 
				securityProperties.getBrowser().getLoginPage(),//放过登录页不过滤，否则报错
				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
				SecurityConstants.SESSION_INVALID_PAGE,
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll() //验证码
				.anyRequest()		//任何请求
				.authenticated()	//都需要身份认证
			.and()
				.csrf().disable() //关闭csrf防护
			.apply(smsCodeAuthenticationSecurityConfig);//把短信验证码配置应用上
	}
}

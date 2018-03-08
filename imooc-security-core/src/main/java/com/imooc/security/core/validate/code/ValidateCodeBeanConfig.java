package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;

/**
 * 配置验证码生成接口ValidateCodeGenerator的实际实现类的Bean
 * ClassName: ValidateCodeBeanConfig 
 * @Description: 
 * 	配置验证码生成接口ValidateCodeGenerator的实际实现类的Bean
 * 	如图片验证码的实现、短信验证码的实现
 * @author lihaoyang
 * @date 2018年3月5日
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * @Description: 
	 * 配置图片验证码生成bean
	 * @ConditionalOnMissingBean注解意思是当spring容器不存在imageCodeGenerator时才给配置一个该bean
	 * 作用是使程序更具可扩展性，该配置类是配置在core模块，这就意味着，如果引用该模块的项目
	 * 如果有一个自己的实现，实现了ValidateCodeGenerator接口，定义了自己的实现，名字也叫imageCodeGenerator时，
	 * 就用应用级别的实现，没有的话就用这个默认实现。
	 * @param @return   
	 * @return ValidateCodeGenerator  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年3月5日
	 */
	@Bean
	@ConditionalOnMissingBean(name="imageCodeGenerator") 
	public ValidateCodeGenerator imageCodeGenerator(){ 
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	/**
	 * 配置短信验证码生成bean
	 * @Description: 
	 * @param @return   
	 * @return SmsCodeSender  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年3月7日
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender(){
		return new DefaultSmsCodeSender();
	}
}

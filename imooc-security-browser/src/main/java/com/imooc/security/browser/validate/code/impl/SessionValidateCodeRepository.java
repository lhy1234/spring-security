package com.imooc.security.browser.validate.code.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeRepository;
import com.imooc.security.core.validate.code.ValidateCodeType;

/**
 * 验证码session存取
 * ClassName: SessionValidateCodeRepository 
 * @Description: 验证码session存取
 * @author lihaoyang
 * @date 2018年3月14日
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	/**
	 * 操作session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	/**
	 * 构建验证码放入session时的key
	 * @Description: 构建验证码放入session时的key
	 * @param @param request  ServletWebRequest
	 * @param @param validateCodeType 验证码类型
	 * @param @return   
	 * @return String  验证码放入session时的key
	 * @throws
	 * @author lihaoyang
	 * @date 2018年3月14日
	 */
	private String getSessionKey(ServletWebRequest request,  ValidateCodeType validateCodeType){
		return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
	}

	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
		sessionStrategy.setAttribute(request, getSessionKey(request,validateCodeType), code);
		logger.info("------->session存进验证码，session key:"+getSessionKey(request,validateCodeType)+" ,code:"+code.getCode());
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
		ValidateCode validateCode = (ValidateCode)sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
		logger.info("------->获取到session验证码，session key:"+getSessionKey(request, validateCodeType)+",code:"+validateCode.getCode());
		return validateCode;
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
		sessionStrategy.removeAttribute(request, getSessionKey(request, validateCodeType));
		logger.info("------->移除session验证码，session key:"+getSessionKey(request, validateCodeType));
	}

}

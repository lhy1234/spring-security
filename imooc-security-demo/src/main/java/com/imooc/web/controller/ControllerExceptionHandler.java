package com.imooc.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.imooc.exception.UserNotExistException;

/**
 * @ControllerAdvice:处理其他controller抛出的异常，都会到这里处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)//处理哪个异常类
	@ResponseBody //返回json
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//状态码 500
	public Map<String,Object> handlerUserNotExistException(UserNotExistException ex){
		Map<String,Object> result = new HashMap<>();
		//通过ex对象拿到异常信息 
		result.put("id", ex.getId()); 
		result.put("message", ex.getMessage());
		return result;
	}
}

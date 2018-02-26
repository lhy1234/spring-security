package com.imooc.exception;

/**
 * 用户不存在异常
 * ClassName: UserNotExistException 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月26日
 */
public class UserNotExistException extends RuntimeException{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	public UserNotExistException(String id){
		super("user not exist!");
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}

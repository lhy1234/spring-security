package com.imooc.security.core.support;

/**
 * 认证处理控制器响应信息
 * ClassName: SimpleResponse 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年2月28日
 */
public class SimpleResponse {

	private Object content;

	public SimpleResponse(Object content) {
		super();
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}

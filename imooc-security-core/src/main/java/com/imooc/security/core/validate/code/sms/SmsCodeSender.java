package com.imooc.security.core.validate.code.sms;

/**
 * 短信验证码发送接口
 * ClassName: SmsCodeSender 
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月7日
 */
public interface SmsCodeSender {

	/**
	 * 发送验证码短信
	 * @Description: 短信发送
	 * @param @param mobile 接收验证码的手机号
	 * @param @param code 验证码
	 * @return void  
	 * @throws
	 * @author lihaoyang
	 * @date 2018年3月7日
	 */
	void send(String mobile,String code);
}

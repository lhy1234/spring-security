package com.imooc.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQUserInfo;

/**
 * 在服务提供商qq和第三方应用之间做用户信息的转换
 * ClassName: QQAdapter 
 * @Description: 
 * 	在服务提供商qq和第三方应用之间做用户信息的转换
 * 	实现 ApiAdapter接口，泛型是API接口，对应我们的QQ接口
 * @author lihaoyang
 * @date 2018年3月6日
 */
public class QQAdapter implements ApiAdapter<QQ>{

	/**
	 * 测试当前api是否可用，测试qq是否可用
	 */
	@Override
	public boolean test(QQ api) {
		//就不掉了，直接true
		return true;
	}

	/**
	 * 	Connection和api之间的适配
	 * ConnectionValues:
	 * 		创建Connection需要的数据项
	 * 从api中获取数据，给ConnectionValues设置值
	 */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		
			//获取用户信息
			QQUserInfo userInfo = api.getUserInfo();
			values.setDisplayName(userInfo.getNickname());//展示名，qq用户名
			values.setImageUrl(userInfo.getFigureurl_1()); //qq头像
			values.setProfileUrl(null); //个人主页，qq没有，微博有
			values.setProviderUserId(userInfo.getOpenId());
	}

	/**
	 * 
	 */
	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 某些社交如微博才有
	 */
	@Override
	public void updateStatus(QQ api, String message) {
		// do nothing
	}

}

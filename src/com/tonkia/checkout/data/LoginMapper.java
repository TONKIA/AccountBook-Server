package com.tonkia.checkout.data;

import java.util.HashMap;
import com.tonkia.checkout.vo.UserInfo;

public interface LoginMapper {
	// 根据手机号获取用户信息
	public UserInfo getUserByPhone(String phone);

	// 注册用户信息和验证码
	public boolean registerIdentifyCode(HashMap<String, String> para);

	//重新获取验证码
	public boolean updateIdentifyCode(HashMap<String, String> para);
	
	// 验证码成功，注册密码
	public boolean registerLogin(HashMap<String, String> para);

}

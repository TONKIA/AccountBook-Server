package com.tonkia.checkout.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tonkia.checkout.data.LoginMapper;
import com.tonkia.checkout.utils.IdentifyCode;
import com.tonkia.checkout.utils.TextUtils;
import com.tonkia.checkout.vo.UserInfo;

@Service("loginService")
public class LoginServiceImple implements LoginService {
	private static final Log logger = LogFactory.getLog(LoginServiceImple.class);

	@Resource
	private LoginMapper loginMapper;

	// 验证码验证时间
	private static final int idTime = 60000;
	// 验证码过期时间
	private static final int idExpireTime = 120000;

	// 获取验证码
	@Override
	public String getIndentifyCode(String phone) {
		// 需要校验手机号码
		if (TextUtils.isMobileNum(phone)) {
			// 生成4位的验证码
			String identifyCode = IdentifyCode.generateIndentifyCode(4);
			// 判断手机号是否注册过
			UserInfo ui = loginMapper.getUserByPhone(phone);
			if (ui != null) {
				// 更新数据库中验证码
				// 60秒更新一次
				long cur = System.currentTimeMillis();
				long interval = cur - ui.getIdentifyTime().getTime();
				if (interval > idTime) {
					HashMap<String, String> para = new HashMap<>();
					para.put("phone", phone);
					para.put("identifyCode", identifyCode);
					if (loginMapper.updateIdentifyCode(para)) {
						logger.info("IndentifyCode(" + phone + ":" + identifyCode + ")");
						// TODO 发送短信
						return "验证码已发送！";
					}
				} else {
					return "还需等待" + (idTime - interval) / 1000 + "s";
				}
			} else {
				// 注册到数据库中
				HashMap<String, String> para = new HashMap<>();
				para.put("phone", phone);
				para.put("identifyCode", identifyCode);
				if (loginMapper.registerIdentifyCode(para)) {
					logger.info("IndentifyCode(" + phone + ":" + identifyCode + ")");
					// TODO 发送短信
					return "验证码已发送！";
				}
			}
		}
		return "验证码发送失败！";
	}

	// 验证验证码 返回
	@Override
	public String login(String phone, String identifyCode) {
		// 需要校验手机号码
		if (TextUtils.isMobileNum(phone)) {
			UserInfo ui = loginMapper.getUserByPhone(phone);
			// 首先判断手机号码存在否
			if (ui != null) {
				// 先判断验证码是否过期
				long cur = System.currentTimeMillis();
				if (cur - ui.getIdentifyTime().getTime() > idExpireTime) {
					ui.setIdentifyCode(null);
				}
				// 是否有验证码
				if (ui.getIdentifyCode() != null) {
					// 验证验证码是否一致
					if (ui.getIdentifyCode().equals(identifyCode)) {
						// 验证码正确，生成密码pwd
						String pwd = IdentifyCode.generateIndentifyCode(10);
						// 注册到数据库中
						HashMap<String, String> para = new HashMap<>();
						para.put("phone", phone);
						para.put("pwd", pwd);
						if (loginMapper.registerLogin(para)) {
							logger.info("login(" + phone + ":" + pwd + ")");
							return pwd;
						}
					}
				}
			}
		}
		return null;
	}

	// 验证用户
	@Override
	public boolean indentify(String phone, String pwd) {
		boolean res = false;
		if (TextUtils.isMobileNum(phone)) {
			UserInfo ui = loginMapper.getUserByPhone(phone);
			if (ui != null) {
				res = ui.getPwd().equals(pwd);
			}
			logger.info("loginIdentify(" + phone + ":" + pwd + ":" + res + ")");
		}
		return res;
	}

}

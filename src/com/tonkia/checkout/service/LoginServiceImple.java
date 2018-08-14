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

	// ��֤����֤ʱ��
	private static final int idTime = 60000;
	// ��֤�����ʱ��
	private static final int idExpireTime = 120000;

	// ��ȡ��֤��
	@Override
	public String getIndentifyCode(String phone) {
		// ��ҪУ���ֻ�����
		if (TextUtils.isMobileNum(phone)) {
			// ����4λ����֤��
			String identifyCode = IdentifyCode.generateIndentifyCode(4);
			// �ж��ֻ����Ƿ�ע���
			UserInfo ui = loginMapper.getUserByPhone(phone);
			if (ui != null) {
				// �������ݿ�����֤��
				// 60�����һ��
				long cur = System.currentTimeMillis();
				long interval = cur - ui.getIdentifyTime().getTime();
				if (interval > idTime) {
					HashMap<String, String> para = new HashMap<>();
					para.put("phone", phone);
					para.put("identifyCode", identifyCode);
					if (loginMapper.updateIdentifyCode(para)) {
						logger.info("IndentifyCode(" + phone + ":" + identifyCode + ")");
						// TODO ���Ͷ���
						return "��֤���ѷ��ͣ�";
					}
				} else {
					return "����ȴ�" + (idTime - interval) / 1000 + "s";
				}
			} else {
				// ע�ᵽ���ݿ���
				HashMap<String, String> para = new HashMap<>();
				para.put("phone", phone);
				para.put("identifyCode", identifyCode);
				if (loginMapper.registerIdentifyCode(para)) {
					logger.info("IndentifyCode(" + phone + ":" + identifyCode + ")");
					// TODO ���Ͷ���
					return "��֤���ѷ��ͣ�";
				}
			}
		}
		return "��֤�뷢��ʧ�ܣ�";
	}

	// ��֤��֤�� ����
	@Override
	public String login(String phone, String identifyCode) {
		// ��ҪУ���ֻ�����
		if (TextUtils.isMobileNum(phone)) {
			UserInfo ui = loginMapper.getUserByPhone(phone);
			// �����ж��ֻ�������ڷ�
			if (ui != null) {
				// ���ж���֤���Ƿ����
				long cur = System.currentTimeMillis();
				if (cur - ui.getIdentifyTime().getTime() > idExpireTime) {
					ui.setIdentifyCode(null);
				}
				// �Ƿ�����֤��
				if (ui.getIdentifyCode() != null) {
					// ��֤��֤���Ƿ�һ��
					if (ui.getIdentifyCode().equals(identifyCode)) {
						// ��֤����ȷ����������pwd
						String pwd = IdentifyCode.generateIndentifyCode(10);
						// ע�ᵽ���ݿ���
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

	// ��֤�û�
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

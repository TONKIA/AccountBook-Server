package com.tonkia.checkout.data;

import java.util.HashMap;
import com.tonkia.checkout.vo.UserInfo;

public interface LoginMapper {
	// �����ֻ��Ż�ȡ�û���Ϣ
	public UserInfo getUserByPhone(String phone);

	// ע���û���Ϣ����֤��
	public boolean registerIdentifyCode(HashMap<String, String> para);

	//���»�ȡ��֤��
	public boolean updateIdentifyCode(HashMap<String, String> para);
	
	// ��֤��ɹ���ע������
	public boolean registerLogin(HashMap<String, String> para);

}

package com.tonkia.checkout.utils;

public class TextUtils {
	public static boolean isMobileNum(String mobileNums) {
		String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"������һλΪ���ֿ����Ǽ���"[0-9]"�������Ϊ0-9�е�һ����"[5,7,9]"��ʾ������5,7,9�е�����һλ,[^4]��ʾ��4������κ�һ��,\\d{9}"��������ǿ�����0��9�����֣���9λ��
		if (mobileNums == null || mobileNums.length() != 11)
			return false;
		else
			return mobileNums.matches(telRegex);
	}
}

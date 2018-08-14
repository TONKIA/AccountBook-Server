package com.tonkia.checkout.service;

public interface LoginService {

	public boolean getIndentifyCode(String phone);

	public String login(String phone, String indentifyCode);

	public boolean indentify(String phone, String pwd);
}

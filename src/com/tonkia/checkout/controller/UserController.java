package com.tonkia.checkout.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tonkia.checkout.service.LoginService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private LoginService loginService;

	// ��ȡ��֤��
	@RequestMapping("/getIdentifyCode")
	public void getIdentifiyCode(String phone, HttpServletResponse response) throws IOException {
		String res = loginService.getIndentifyCode(phone);
		// ��ʱ�ȷ���
		response.getWriter().write("" + res);
	}

	// ʹ����֤���¼����ȡpwd
	@RequestMapping("/login")
	public void login(String phone, String identifyCode, HttpServletResponse response) throws IOException {
		String pwd = loginService.login(phone, identifyCode);
		if (pwd != null) {
			response.getWriter().write(pwd);
		}
	}

	// ʹ��������֤��¼
	@RequestMapping("/identify")
	public void indentify(String phone, String password, HttpServletResponse response) throws IOException {
		boolean res = loginService.indentify(phone, password);
		response.getWriter().write("res:" + res);
	}
}

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

	// 获取验证码
	@RequestMapping("/getIdentifyCode")
	public void getIdentifiyCode(String phone, HttpServletResponse response) throws IOException {
		String res = loginService.getIndentifyCode(phone);
		// 暂时先返回
		response.getWriter().write("" + res);
	}

	// 使用验证码登录，获取pwd
	@RequestMapping("/login")
	public void login(String phone, String identifyCode, HttpServletResponse response) throws IOException {
		String pwd = loginService.login(phone, identifyCode);
		if (pwd != null) {
			response.getWriter().write(pwd);
		}
	}

	// 使用密码验证登录
	@RequestMapping("/identify")
	public void indentify(String phone, String password, HttpServletResponse response) throws IOException {
		boolean res = loginService.indentify(phone, password);
		response.getWriter().write("res:" + res);
	}
}

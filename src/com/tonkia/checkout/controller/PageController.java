package com.tonkia.checkout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/{path}")
	public String page(@PathVariable("path") String path) {
		return path;
	}

	@RequestMapping("/")
	public String main() {
		return "main";
	}

}

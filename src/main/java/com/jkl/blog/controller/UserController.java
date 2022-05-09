package com.jkl.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	//인증이 안 된 사용자들은 /auth 붙은 경로, /만(=index.jsp) 출입 가능
	//static 파일 안에 있는 /js/**, /css/**, /image/** 도 출입 가능
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		
		return "user/updateForm";
	}
}

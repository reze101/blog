package com.jkl.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jkl.blog.dto.ResponseDto;
import com.jkl.blog.model.User;
import com.jkl.blog.service.UserService;

@RestController // 데이터만 받을것이므로 RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

//	@Autowired
//	private HttpSession httpSession;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("user api controller : save 호출");

		// db insert
		userService.save(user);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/user")						//json data 받을때 @RequestBody 필요, 
	public ResponseDto<Integer> update(@RequestBody User user
//			, @AuthenticationPrincipal PrincipalDetail pricipal, HttpSession session
			) {

		System.out.println("user api controller : save 호출");

		// db insert
		userService.update(user);
		//service 로직이 끝나면 트잭도 종료되기 때문에 db insert 쿼리 날아가서 db값 변경됨 
		//하지만 세션에 있는 회원정보는 아직 변하지 않았으므로 세션에 있는 회원정보를 업데이트 해야 함 
		//update 정보 세션 등록 
		org.springframework.security.core.Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	/*
	 * 
	 * 기본 로그인 (not security)
	 * 
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user){ // public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession httpSession){ //매개변수에 적지
	 * 않고 @Autowired가능 System.out.println("user api controller : login 호출"); User
	 * principal = userService.login(user);
	 * 
	 * if(principal != null ) { httpSession.setAttribute("principal", principal); }
	 * 
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 * 
	 */

}

package com.jkl.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jkl.blog.config.auth.PrincipalDetail;
import com.jkl.blog.dto.ResponseDto;
import com.jkl.blog.model.Board;
import com.jkl.blog.model.RoleType;
import com.jkl.blog.model.User;
import com.jkl.blog.service.BoardService;
import com.jkl.blog.service.UserService;

@RestController	//데이터만 받을것이므로 RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
//	@Autowired
//	private HttpSession httpSession;

	@PostMapping("/api/board")																//스프링 시큐리티 세션에 있는 userdetails 객체 
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail priDetail ) {
		System.out.println("user api controller : save 호출");
		
		//db insert
		boardService.save(board, priDetail.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id, @RequestBody Board board){
		
		boardService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		
		boardService.update(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); 
	}
	
	
	
	
	
/*	
 
 * 기본 로그인 (not security)
 
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession httpSession){	//매개변수에 적지 않고 @Autowired가능 
			System.out.println("user api controller : login 호출");
			User principal = userService.login(user);
			
			if(principal != null ) {
				httpSession.setAttribute("principal", principal);
			}
			
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

*/
	
	
	
	
	
	
	
	
	
	
	
	
}

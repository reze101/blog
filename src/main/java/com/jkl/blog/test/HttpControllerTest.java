package com.jkl.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> html파일로 응답 : @Controller
//사용자가 요청 -> data로 응답 ; @RestController
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String LombokTest() {
		Model m = Model.builder().username("jiji").password("ijij").email("ijij@hanmail.net").build();
		System.out.println(TAG + "getter : " + m.getUsername());
		m.setUsername("ijij");
		System.out.println(TAG + "setter" + m.getUsername());
		return "lombok Test 완";
	}
	
	//인터넷 브라우저 요정은 get요청만 할 수 있음 (http 405)
//	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, String username) {	//@RequestParam : get요청 queryString
//		return "get요청 : " + id + "username : " + username;
//	}
	
	@GetMapping("/http/get")
	public String getTest(Model m) {
		return "get요청 : " + m.getId() + ", username : " + m.getUsername() + ", email : " + m.getEmail();
	}
	
//	@PostMapping("/http/post")
//	public String postTest(Model m) {
//		return "post요청 : " + m.getId() + ", username : " + m.getUsername() + ", email : " + m.getEmail();
//	}
	
//	@PostMapping("/http/post")
//	public String postTest(@RequestBody String text) {	//@RequestBody : body값 
//		return "post요청 : " + text;
//	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Model m) {
		return "post요청 : id : " + m.getId() + ", username : " + m.getUsername() + ", email : " + m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
	
}

package com.jkl.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp/home")
	public String tempHome() {
			System.out.println("temphome");
			
			//yml파일에 파일 경로 설정을 바꾸지않을 때의 파일리턴 기본경로 : src/main/resources/static
			//리턴명 : /home.html
			
			//@RestController : return 에 적힌 String을 그대로 return
			//@Controller : return에 적힌 파일을 return
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		System.out.println("sea");
		return "/sea.jpg";
	}
	
	
	//jsp파일을 return하기 위해선 jasper(jsp템플릿엔진) pom.xml에 추가해야 사용 가능 
	//또한 static 폴더엔 정적 파일만 인식 가능함 (이미지, html ...) -> jsp는 동적파일(컴파일 필요)이므로 찾지 못함 
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		System.out.println("jsp");
		return "test";
	}
}

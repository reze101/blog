package com.jkl.blog.controller;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jkl.blog.model.OAuthToken;

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
	
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) {	//@ResponseBody : Data를 리턴해주는 컨트롤러 함수가 됨
		
		//인증토큰 발급을 위해 post 방식으로 key=value 형식의 데이터를 카카오쪽으로 요청해야 함
		//http 요청
		RestTemplate rt = new RestTemplate();	
		
		//http헤더 생성
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//body에 값 담을 object 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "60af53dd62335119b0e681ffbcc766ee");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//body와 header를 담고있는 객체 생성 
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		
		//요청
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class		//응답 받을 타입 
				);
		
		//Gson, Json Simple, ObjectMapper : json data를 java object 로 처리하기 위함 
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("카카오엑세스 토큰 : "+oauthToken.getAccess_token());
		return response.getBody();		

	}
}

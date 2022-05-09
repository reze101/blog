package com.jkl.blog.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jkl.blog.model.User;
import com.jkl.blog.repository.UserRepository;

@Service		//bean 등록 
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//security 가 로그인 요청을 가로 챌 때 username, password 변수 2개를 가로채는데,
	//password는 알아서 처리(맞/틀)하므로 username만 db에 있는지 확인 
	@Override		//username확인 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
									.orElseThrow(()->{
										return new UsernameNotFoundException(username + " (이)라는 사용자를 찾을 수 없습니다.");
									});
		return new PrincipalDetail(principal);		//security 고유 세션에 user정보(UserDetails타입) 저장됨 
	}

}

package com.jkl.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jkl.blog.model.RoleType;
import com.jkl.blog.model.User;
import com.jkl.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;	//패스워드 암호화 
	
	
	@Transactional
	public void save(User user) {
		
		String rawPassword = user.getPassword();	//비밀번호 원문
		String encPassword = encoder.encode(rawPassword);	//해쉬화 된 비밀번호 
		user.setPassword(encPassword);
		user.setRole(RoleType.ADMIN);
		userRepository.save(user);
	}


	@Transactional
	public void update(User user) {
		
		//jpa 수정법 : 영속성 컨텍스트에 User 오브젝트를 영속화 시키고 영속화 된 User 오브젝트를 수정 
		//select를 해서 User 오브젝트를 DB로 가져오는 이유 : 영속화 하기 위하여 
		//영속화 된 오브젝트를 변경하면 더티체킹을 통하여 자동으로 db에 update쿼리를 날림 
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패 ");
		});

		// 패스워드 암호화 해아 함 
		String rawPassword = user.getPassword();	
		String encPassword = encoder.encode(rawPassword);
		
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//회원 수정 함수 종료 = 서비스 종료 = 트잭 종료 = 자동 커밋 
		//영속화 된 persistance 객체의 변화 감지시 더티체킹 후 자동 update후 커밋됨 
		
	}
		

	/*
	 * 기본 로그인 
	 * 
	@Transactional(readOnly = true)	//select할 때 트랜잭션 시작 , 서비스 종료시  트랜잭션 종료 (데이터 정합성 유지가능)
	public User login(User user) {
		return userRepository.findAllByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	*/
}

package com.jkl.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jkl.blog.model.User;

//user테이블이 관리하는 repository, user테이블의 pk는 integer
//자동으로 bean등록되므로 @Repository 어노테이션 생략가능 
public interface UserRepository extends JpaRepository<User,  Integer> {
	
	//= select * from user where username = ?;
	Optional<User> findByUsername(String username);
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 기본 로그인 
	 * 
	//JPA 네이밍 전략 
	// select * from usesr where username = ? and password = ?;	
	User findAllByUsernameAndPassword(String username, String password);
	//위 쿼리는 아래와 같음 (nativeQuery)
	
	//@Query(value = "select * from usesr where username = ? and password = ?", nativeQuery = t)
	//User Login(String username, Strind password);
	*/
	
}

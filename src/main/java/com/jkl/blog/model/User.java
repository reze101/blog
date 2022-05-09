package com.jkl.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	// User class가 Mysql에 테이블생성됨 
//@DynamicInsert	//null값 안 들어가게 방지 -> insert시 null 인 필드 제외 
public class User {
	
	@Id	//Primary kew
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//프로젝트와 연결된 db의 넘버링 전략 그대로 사용 
	private int id;	//시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;	//아이디
	
	@Column(nullable = false, length = 1000)	//비밀번호 암호하
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)	//db에는 RoleType이라는 데이터타입이 없으므로 String임을 선언 
	private RoleType role;	//Enum 사용하는게 좋음 (사용자 권한)
	
	@CreationTimestamp	//시간 자동입력 
	private Timestamp createDate;
	
}

package com.jkl.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	// User class가 Mysql에 테이블생성됨 
public class Reply {
	
	@Id	//Primary kew
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//프로젝트와 연결된 db의 넘버링 전략 그대로 사용 
	private int id;	//시퀀스, auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;	
	
	@ManyToOne	//many : 여려개의 reply, one : 하나의 board
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne	//many : 여려개의 reply, one : 하나의 user
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}

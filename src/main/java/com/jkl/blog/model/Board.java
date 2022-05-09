package com.jkl.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//mysql : auto-increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;

	@Lob	//섬머노트 라이브러리 : html태그와 함께 디자인되므로 용량이 큼 , lob : 대용량데이터 
	private String content;
	
	//@ColumnDefault("0")
	private int count;	//조회수
	
	@ManyToOne(fetch = FetchType.EAGER)	//many : board, one : user -> 한 명의 유저는 여러개의 board 사용 가능 //eager 전략을 써서 자동으로 user의 내용 다 가져옴 <=> LAZY
	@JoinColumn(name="userId")
	private User user;
	
	//@JoinColumn(name = "replyId")	: fk 필요없으므로 
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)	//mapped by : 연관관계의 주인이 아님(=fk 가 아님) -> db에 컬럼을 만들지 않음(컬럼을 만들게 되면 여러개의 값이 들어올 경우 원자성 위배) 
	private List<Reply> reply;	//하나의 board에는 여러개의 reply가 올 수 있으므로 반환형은 Reply라는 Object를 담은 List컬렉션임 
	
	@CreationTimestamp
	private Timestamp createDate;
	
}

package com.jkl.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jkl.blog.model.Board;

//board테이블이 관리하는 repository, board테이블의 pk는 indeger
//자동으로 bean등록되므로 @Repository 어노테이션 생략가능 
public interface BoardRepository extends JpaRepository<Board,  Integer> {
	
}

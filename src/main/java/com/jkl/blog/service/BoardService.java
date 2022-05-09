package com.jkl.blog.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jkl.blog.model.Board;
import com.jkl.blog.model.User;
import com.jkl.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void save(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);

	}
	
	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board findById(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("상세 보기 실패 : " + id + " (이)라는 사용자를 찾을 수 없습니다. ");
		});
	}

	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public  void update(int id, Board board) {
		Board selectedBoard = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패 : " + id + " (이)라는 사용자를 찾을 수 없습니다. ");
		});
		selectedBoard.setTitle(board.getTitle());
		selectedBoard.setContent(board.getContent());
		//해당 함수 종료시에(= service 종료시 ) 트랜잭션이 종료됨 
		//이 떄 더티체킹이 되어 자동 commit 됨 
		//boardRepository.save(selectedBoard);
		
	}
}

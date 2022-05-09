package com.jkl.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.jkl.blog.model.Board;
import com.jkl.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
//	@Autowired
//	private PrincipalDetail principalDetail;		//컨트롤러에서 세션에 접근할 때 
	
//	public String index(@AuthenticationPrincipal PrincipalDetail principalDetail) {
	@GetMapping({"","/"})	//controller에서 세션을 어떻게 찾을까? 
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {	//컨트롤러에서 세션에 접근할 때 
//		System.out.println("컨트롤러 세션에 등록되어있는 로그인 사용자 아이디 : " + principalDetail.getUsername());
//		System.out.println("컨트롤러 세션에 등록되어있는 로그인 사용자 아이디 : " + principalDetail.getPassword());
		
		
		Page<Board> boardList = boardService.boardList(pageable);
//		List<Board> boardList = boardPageList.getContent();
		model.addAttribute("boardList", boardList);		//@Controller가 알맞은 html파일을 반환할 때 viewResolver 작동, model에 담은 정보를 view로 이동시킬 수 있음 
		
		return "index";
	}
	
	//USER권한 필요 
	@GetMapping("/board/saveForm")
	public String saveForm() {	//글쓰기 창으로 이동 
		return "/board/saveForm";
	}

	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		boardService.findById(id);
		model.addAttribute("boardDetail", boardService.findById(id));
		return "/board/detail"; 
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.findById(id));
		return "board/updateForm";
	}
	
	
}

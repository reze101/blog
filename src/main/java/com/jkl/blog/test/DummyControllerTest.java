package com.jkl.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jkl.blog.model.RoleType;
import com.jkl.blog.model.User;
import com.jkl.blog.repository.UserRepository;

@RestController	// html파일이 아니라 data를 리턴해주는 Controller 
public class DummyControllerTest {

	@Autowired		//di
	private UserRepository userRepository;
	
	// {id} 주소로 파라미터를 전달받을 수 있음
	//		ex) https://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//Optional<User> user = userRepository.findById(id);
			//Optional : /user/3을 데이터베이스에서 못 찾아오게 되면 user는 null 이 됨
			//				null을 가져오면 프로그램에 문제가 생길 수 있으므로 
			//				Optional 객체로 감싸서 null인지 아닌지 판단하여 return
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		
		//위의Supplier를 생략한 람다식 
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
//	});
		
		//웹 브라우저에서 요청해서 user 라는 자바 오브젝트를 웹 브라우저에게 return -> 웹브라우저는 자바오브젝트를 이해할 수 없음(html같은거만 이해함)
		//따라서 웹브라우저가 이해할 수 있는 json으로 변환하여 return
		//spring boot 는 MessageConverter 가 응답시 자동 작동하여 Jackson 라이브러리 호출 -> user 오브젝트를 json으로 변환하여 return함 
		return user;
	}
	
	//여러건의 데이터 select
	
	@GetMapping("/dummy/users")
	public List<User>  list(){
//		List<User> users = userRepository.findAll();
//		return users;
		return userRepository.findAll();
	}
	
//	//한 페이지당 2건의 데이터 리턴 (user와 페이지)
//	@GetMapping("/dummy/user")
//	public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable){
//		Page<User> users =  userRepository.findAll(pageable);
//		return users;
//	}
	
	//한 페이지당 2건의 데이터 리턴 (유저만)
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable){
//		List<User> users =  userRepository.findAll(pageable).getContent();
		Page<User> users =  userRepository.findAll(pageable);
		return users;
	}
	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {	//원래 매개변수에 @RequestParam 어노테이션 필요 (http body 값과 변수명 맞춰주면 생략 가능)
	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createdate : " + user.getCreateDate());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 성공!";
	}
	
	//update
	//email, password 수정
	@Transactional	//save 함수 호출 없이 더티체킹을 통해 수정쿼리를 날림 (함수 종료시에 자동 커밋)
	@PutMapping("/dummy/user/{id}")						//@RequestBody ; json데이터 받을 때 사용 (json데이터를 받으면 MessageConverter가 json -> java Object로 바꿈)
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		//결과 : set 을 걸어주지 않은 컬럼들은 null로 수정이 됨 
				//		System.out.println("id : " + id);
				//		System.out.println("password : " + requestUser.getPassword());
				//		System.out.println("email : " + requestUser.getEmail());

//		requestUser.setId(id);
//		userRepository.save(requestUser);
		
		//결과 : 정상적으로 update됨
		User user = userRepository.findById(id).orElseThrow(()->{
			throw new IllegalArgumentException("수정에 실패");
		});	//영속성 컨텍스트에서 영속화 함
		
		//값변경 -> @Transactional 사용시 함수 종료될 때 더티체킹하여 update 
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//userRepository.save(user);
		
		//save 함수
		//	id를 전달하지 않으면 insert
		//	id를 전달하면
		//		해당 id에 대한 데이터가 없으면 insert
		//		해당 id에 대한 데이터가 있으면 update
		return user;
	}
	
	//삭제
	@DeleteMapping("/dummy/user/{id}")		
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제 실패 : 해당 id가 없습니다. ";
		}
		
		return "삭제 성공! id : " + id;
	}
	
}

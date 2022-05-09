package com.jkl.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jkl.blog.model.User;

import lombok.Getter;


//스프링 시큐리티가 /auth/loginProc로 오는 로그인 요청을 가로채서 로그인을 진행하고, 로그인이 완료되면 UserDetails 타입의 오브젝를 스프링 시큐리티 고유 세션 저장소에 저장해줌  
@Getter
public class PrincipalDetail implements UserDetails{
	
	private User user; 	//컴포지션
					//boardController에서 user정보를 꺼내기 위하여 @getter 필요

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	
	//계정이 만료되었는지/ 만료되지 않았는지를 return 
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;		//true : 계정안만료 , false : 계정만료 
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;		//true : 계정안잠김 , false : 계정잠김  
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;		//true : 비밀번호 안만료 , false : 비밀번호 만료 
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;		//true : 계정 활성화 , false : 계정 비활성화
	}

	//계정 권한 반환 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>();
		/*
		collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return "ROLE_" + user.getRole();		//return값 양식 : ROLE_ADMIN ... 
			}
		});
		
		람다식으로 표현할 시 아래의 코드와 같음 
		*/
		collectors.add(()->{
			return "ROLE_" + user.getRole();
		});
		return collectors;
	
	
	}
}

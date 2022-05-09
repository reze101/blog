package com.jkl.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jkl.blog.config.auth.PrincipalDetailService;

@Configuration	//스프링 컨테이너에서 객체를 관리 할 수 있도록 bean 등록 
@EnableWebSecurity	//요청이 controller를 타기 전에 요청을 가로채서 권한 인증을 가짐 (security filter 등록, 설정가능 ) 
@EnableGlobalMethodSecurity(prePostEnabled = true)		//특정 주소로 접근시 권한/ 인증을 미리 체크함 
public class SecurityConfig extends  WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	//회원가입
	@Bean		//return되는 new BCryptPasswordEncoder();를 spring이 관리하므로 ioc 가능 
	public BCryptPasswordEncoder encoderPWD() {	//가입할 떄 비밀번호를 해쉬로 암호화 함 
		return new BCryptPasswordEncoder();
	}

	//security가 대신 로그인 할 떄 password를 가로챔
	//가로 챈 password는 해쉬가 되어 회원가입 된 password이므로 
	//그 password 가 어떻게 해쉬가 되었는지 알아야 같은 해쉬로 암호화 해서 db와 비교하여 로그인 처리 할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()							//cdrf토큰 비활성화 
			.authorizeRequests()					//request가 들어올 때  
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")		//여기 주소로 request가 들어오면 
				.permitAll()								//모두 허용하겠다 (security 인증 필요 x)
				.anyRequest()							//그 외 모든 다른 request는
				.authenticated()						//인증이 필요함 
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")					//사용자 정의 로그인페이지(인증이 안된 상태일때 인증이 필요한 요청 = "/","/auth/**","/js/**","/css/**","/image/**" 를 제외한 요청이 올때  )
				.loginProcessingUrl("/auth/loginProc")	//spring security 가 /auth/loginProc로 요청 오는 로그인 가로채서 대신 로그인 처리 해줌  
				.defaultSuccessUrl("/");								//로그인이 성공 시 이동할 페이지 url (security가 loginProc에서 가로채서 로그인 처리 후 정상일 때 이동할 페이지 = "/")
				
	}			
	
	
	
	
	
	
	
}

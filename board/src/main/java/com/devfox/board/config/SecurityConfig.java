package com.devfox.board.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	
	
	private final CustomAuthFailureHandler customAuthFailureHandler;
	//private final AuthenticationFailureHandler authenticationFailureHandler;
	//private final PrincipalOAuthUserService principalOAuthUserService;
	
	//비밀번호 암호화
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			// Cross-Site Request Forgery: 보호 기능 비활성화
			.csrf().disable()
			// iframe 으로 접근이 안되도록 하는 설정을 비활성화
			.headers().frameOptions().disable()
			.and()
			// URL 별 권한접근 제어
			.authorizeRequests()
			.antMatchers("/", "/member/join", "/member/login", "/member/login-failed", "/member/logout",
					"/member/idCheck").permitAll()
			.antMatchers("/css/*", "/js/*", "/favioon.ioo", "/error").permitAll()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			// 이외의 모든 경로는 인증을 받아서 접근 가능
			.anyRequest().authenticated()
			.and()
			// 폼 로그인 방식을 사용
			.formLogin()
			// 아이디 필드의 기본값은 username이고 다른 이름을 사용할 시 이름을 지정
			.usernameParameter("member_id")
			// 사용자가 만든 로그인 페이지를 사용
			// 설정을 하지 않으면 기본값이 "/login"이기 때문에 스프링이 사용하는 기본 로그인 페이지가 호출된다.
			.loginPage("/member/login")	//GET 방식
			// 로그인 인증 처리를 하는 URL
			.loginProcessingUrl("/member/login")  //POST방식
			// 로그인에 성공했을 때 이용할 URL
			.defaultSuccessUrl("/member/login-success")
			//.failureUrl("/member/login-failed")
			.failureHandler(customAuthFailureHandler)
			.and()
			.logout()
			//로그아웃 URL 지정
			.logoutUrl("/member/logout")
			//로그아웃 성공 후 리다이렉트 될 주소
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
			
			
		return http.build();
		
	}

}

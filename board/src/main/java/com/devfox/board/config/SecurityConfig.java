package com.devfox.board.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	
	
	private final CustomAuthFailureHandler customAuthFailureHandler;
	
	//パスワード暗号化メソッドをbeanとして登録
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			// Cross-Site Request Forgery:保護機能を不活化
			.csrf().disable()
			// iframeでアクセスできないようにする設定を不活化
			.headers().frameOptions().disable()
			.and()
			// URL別権限アクセス制御
			.authorizeRequests()
			.antMatchers("/", "/member/join", "/member/login", "/member/login-failed", "/member/logout",
					"/member/idCheck","/board/*","/reply/*").permitAll()
			.antMatchers("/css/*", "/js/*", "/favioon.ioo", "/error").permitAll()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			// それ以外のすべての経路は認証を受けてアクセス可能
			.anyRequest().authenticated()
			.and()
			// フォームログイン方式を使用
			.formLogin()
			// IDフィールドのデフォルト値はusernameで、別の名前を使用する場合は名前を指定します
			.usernameParameter("member_id")
			// ユーザーが作成したログインページを使用
			// 設定をしないとデフォルト値が"/login"であるため、スプリングが使用するデフォルトログインページが呼び出される。
			.loginPage("/member/login")	//GET 방식
			// ログイン認証処理を行うURL
			.loginProcessingUrl("/member/login")  //POST방식
			// ログインに成功したときに利用するURL
			.defaultSuccessUrl("/member/login-success")
			//.failureUrl("/member/login-failed")
			.failureHandler(customAuthFailureHandler)
			.and()
			.logout()
			//ログアウトURL指定
			.logoutUrl("/member/logout")
			//ログアウト成功後にリダイレクトされるアドレス
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
			
			
		return http.build();
		
	}

}

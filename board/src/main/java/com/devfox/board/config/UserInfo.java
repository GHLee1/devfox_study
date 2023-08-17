package com.devfox.board.config;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.devfox.board.model.member.Member;

import lombok.Getter;



@Getter
public class UserInfo implements UserDetails {

	private Member member;
	
	public UserInfo (Member member) {
		this.member = member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자의 권한을 리턴하는 메서드
		Collection<GrantedAuthority> collect = new ArrayList<>();
		//collect.add(new SimpleGrantedAuthority(member.getRole().name()));
		return null;
	}

	@Override
	public String getPassword() {
		// 패스워드를 리턴해주는 메서드
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// 아이디(member_id)를 리턴하는 메서드
		return member.getMember_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정의 기한 만료 여부 - 만료가 안되었는지 물어보는 거라서 true로 리턴해줘야 만료가 안된거라고 하는거임.
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정의 잠금 여부 - 계정이 안잠겨있냐고 물어보는 거임(만약에 비밀번호를 3번이상 잘못입력하는 경우 잠기는지 안잠기는지
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 접속 권한 만료 여부 - 30분지나면 로그아웃?
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정 사용 가능 여부 - 탈퇴한 계정인지 아닌지
		return true;
	}

}

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
	
	//今は使わないでnullをリターン
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// ユーザーの権限をリターンするメソッド
		Collection<GrantedAuthority> collect = new ArrayList<>();
		//collect.add(new SimpleGrantedAuthority(member.getRole().name()));
		return null;
	}

	@Override
	public String getPassword() {
		// パスワードをリターンするメソッド
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// ID(member_id)をリターンするメソッド
		return member.getMember_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		// ユーザーの期限切れの有無
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// ユーザーのロックの有無 
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// アクセス権限の有効期限の有無
		return true;
	}

	@Override
	public boolean isEnabled() {
		// ユーザーの使用可否
		return true;
	}

}

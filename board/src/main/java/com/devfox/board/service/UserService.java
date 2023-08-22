package com.devfox.board.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devfox.board.config.UserInfo;
import com.devfox.board.model.member.Member;
import com.devfox.board.repository.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 *  ログインフォームでIDとパスワードを入力してログインリクエストをすると、
 *  UserDetailsServiceのloadUserByUsernameメソッドを自動的に呼び出します。
 * */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
	
	private final MemberMapper memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername: {}", username);	//Username = member_id
		Member member = memberRepository.findMember(username);
		if(member != null) {
			return new UserInfo(member);
		}
		throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
	}
	
	

}

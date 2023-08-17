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
 *  로그인 폼에서 아이디와 패스워드를 입력하고 로그인 요청을 하면 UserDetailsService의
 *  loadUserByUsername 메소드를 자동으로 호출한다.
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

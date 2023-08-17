package com.devfox.board.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devfox.board.model.member.Member;
import com.devfox.board.repository.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	
	//아이디로 회원정보 조회
	
	public Member findMemberById(String member_id) {
		return memberMapper.findMember(member_id);
	}
	
	//회원정보등록
	public void saveMember(Member member) {
		String rawPassword = member.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		member.setPassword(encPassword);
		memberMapper.saveMember(member);
		
	}
	
	
}

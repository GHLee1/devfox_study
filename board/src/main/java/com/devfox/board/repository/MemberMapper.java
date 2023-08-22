package com.devfox.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devfox.board.model.member.Member;

@Mapper
public interface MemberMapper {
    void saveMember(Member member);
    Member findMember(String member_id);
    List<String> findAllMemberId();
    void updateMember(Member updateMember);
    int idCheck(String member_id);
}

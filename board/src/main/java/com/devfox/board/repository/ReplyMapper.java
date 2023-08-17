package com.devfox.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devfox.board.model.reply.Reply;



@Mapper
public interface ReplyMapper {
	// 리플 등록
	void saveReply(Reply reply);
	
	// 리플 읽기
	Reply findReply(Long reply_id);
	
	// 리플 목록
	List<Reply> findReplies(Long board_id);
	
	// 리플 수정
	void updateReply(Reply reply);
	
	// 리플 삭제
	void removeReply(Long reply_id);
}










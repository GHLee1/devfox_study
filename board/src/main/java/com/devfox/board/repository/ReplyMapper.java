package com.devfox.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devfox.board.model.reply.Reply;



@Mapper
public interface ReplyMapper {
	// リップルの登録
	void saveReply(Reply reply);
	
	// リップル読み取り
	Reply findReply(Long reply_id);
	
	// リップルのリスト
	List<Reply> findReplies(Long board_id);
	
	// リップルの修正
	void updateReply(Reply reply);
	
	// リップルの削除
	void removeReply(Long reply_id);
}










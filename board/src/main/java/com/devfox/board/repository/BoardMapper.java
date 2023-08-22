package com.devfox.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.devfox.board.model.board.Board;

@Mapper
public interface BoardMapper {
	
	 	  //　投稿の保存
		  void saveBoard(Board board); 
		  // 全体の投稿数 
		  int getTotal(String searchText);
		  // 全体の投稿数を探し 
	 	  List<Board> findBoards(String searchText, RowBounds rowBounds); 
	 	  // アイディーを探し 
	 	  Board findBoard(Long board_id); 
	 	  // 投稿の修正 
	 	  void updateBoard(Board updateReview); 
	 	  // 投稿の削除 
	 	  void removeBoard(Long board_id);
		  // 私の全体の投稿数を探し 
	 	  List<Board> findBoardsByMemberId(String member_id);
}

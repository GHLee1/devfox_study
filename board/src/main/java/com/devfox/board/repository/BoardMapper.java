package com.devfox.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.devfox.board.model.board.Board;

@Mapper
public interface BoardMapper {
	
	
	 	  //게시글 저장
		  void saveBoard(Board board); 
		  // 전체 게시글 갯수 
		  int getTotal(String searchText);
		  // 전체 게시글 검색 
	 	  List<Board> findBoards(String searchText, RowBounds rowBounds); 
	 	  // 게시글 아이디로 검색 
	 	  Board findBoard(Long board_id); 
	 	  // 게시글 수정 
	 	  void updateBoard(Board updateReview); 
	 	  // 게시글 삭제 
	 	  void removeBoard(Long board_id);
		  // Member ID로 작성한 글 찾기 
	 	  List<Board> findBoardsByMemberId(String member_id);
		  		  
}

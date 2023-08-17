package com.devfox.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfox.board.model.board.Board;
import com.devfox.board.repository.BoardMapper;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardMapper boardMapper;

    public void saveboard(Board board) {
    	boardMapper.saveBoard(board);
    }

    public List<Board> findboards(String searchText, int startRecord, int countPerPage) {
        // 전체 검색 결과 중 시작 위치와 갯수
        RowBounds rowBounds = new RowBounds(startRecord, countPerPage);
        return boardMapper.findBoards(searchText, rowBounds);
    }

    
    public Board findboard(Long board_id) {
        return boardMapper.findBoard(board_id);
    }

    public Board readboard(Long board_id) {
    	Board board = findboard(board_id);
    	board.addHit();
        updateboard(board);
        return board;
    }

    @Transactional
    public void updateboard(Board updateboard) {
        if (updateboard != null) {
            //log.info("board_id5: {}", updateboard);
            boardMapper.updateBoard(updateboard);
        }
    }

    @Transactional
    public void removeboard(Long board_id) {
        boardMapper.removeBoard(board_id);	
    }
    

    public int getTotal(String searchText) {
        return boardMapper.getTotal(searchText);
    }

	public List<Board> findBoardsByMemberId(String member_id) {
		return boardMapper.findBoardsByMemberId(member_id);
	}

    
}

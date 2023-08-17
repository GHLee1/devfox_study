package com.devfox.board.model.board;

import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

@Data
public class Board {
	@NotBlank
    private Long board_id;
	@NotBlank
    private String member_id;
    private String title;
    private String contents;
    private Long hit;
    private LocalDateTime created_time;
    
    
    public static BoardUpdateForm toBoardUpdateForm(Board board) {
    	BoardUpdateForm boardUpdateForm = new BoardUpdateForm();
    	
        boardUpdateForm.setBoard_id(board.getBoard_id());
        boardUpdateForm.setTitle(board.getTitle());
        boardUpdateForm.setContents(board.getContents());
        boardUpdateForm.setMember_id(board.getMember_id());
        boardUpdateForm.setCreated_time(board.getCreated_time());
        boardUpdateForm.setHit(board.getHit());
        return boardUpdateForm;
    }


    public void addHit() {
		this.hit++;
    }

	
}

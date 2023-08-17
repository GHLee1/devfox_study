package com.devfox.board.model.board;

import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

@Data
public class Board {
	@NotBlank
    private Long review_id;
	@NotBlank
    private String member_id;
    private String title;
    private String contents;
    private LocalDateTime created_time;
    
    
    public static BoardUpdateForm toReviewUpdateForm(Board board) {
    	BoardUpdateForm boardUpdateForm = new BoardUpdateForm();
    	
        boardUpdateForm.setReview_id(board.getReview_id());
        boardUpdateForm.setTitle(board.getTitle());
        boardUpdateForm.setContents(board.getContents());
        boardUpdateForm.setMember_id(board.getMember_id());
        boardUpdateForm.setCreated_time(board.getCreated_time());

        return boardUpdateForm;
    }

	
}

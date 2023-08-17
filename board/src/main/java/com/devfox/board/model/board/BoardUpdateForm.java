package com.devfox.board.model.board;

import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;


@Data
public class BoardUpdateForm {
	private Long review_id;
    private String member_id;
    
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    private Long hit;
    private Long review_like;
    private LocalDateTime created_time;
    private boolean isFileRemoved;
    private String review_place;

}


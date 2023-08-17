package com.devfox.board.model.board;

import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;


@Data
public class BoardUpdateForm {
	private Long board_id;
    private String member_id;
    
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    private Long hit;
    private LocalDateTime created_time;

}


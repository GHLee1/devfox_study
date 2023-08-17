package com.devfox.board.model.reply;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReplyDto {
	private Long reply_id;
    private Long review_id;
    private String member_id;
    private String content;
    private LocalDateTime created_time;
    private boolean isWriter;
}	

package com.devfox.board.model.reply;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Reply {
	private Long reply_id;		
	private Long board_id;		
	private String member_id;	
	private String content;		
	private LocalDateTime created_time;	
	
	public static ReplyDto toDto(Reply reply) {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setReply_id(reply.getReply_id());
        replyDto.setReview_id(reply.getBoard_id());
        replyDto.setMember_id(reply.getMember_id());
        replyDto.setContent(reply.getContent());
        replyDto.setCreated_time(reply.getCreated_time());

        return replyDto;
    }
}	

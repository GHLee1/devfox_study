package com.devfox.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.devfox.board.config.UserInfo;
import com.devfox.board.model.member.Member;
import com.devfox.board.model.reply.Reply;
import com.devfox.board.model.reply.ReplyDto;
import com.devfox.board.repository.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("reply")
@RestController
public class ReplyRestController {
	
	private final ReplyMapper replyMapper;
	
	// 리플 등록
	@PostMapping("{review_id}")
	public ResponseEntity<String> writeReply(@PathVariable Long review_id,
											 @ModelAttribute Reply reply,
											 @AuthenticationPrincipal UserInfo userInfo) {
		log.info("reply: {}", reply);
		reply.setBoard_id(review_id);
		reply.setMember_id(userInfo.getUsername());
		
		replyMapper.saveReply(reply);
		
		return ResponseEntity.ok("등록 성공");
	}
	
	// 리플 읽기 (/reply/게시물아이디/리플아이디)
	@GetMapping("{review_id}/{reply_id}")
	public ResponseEntity<Reply> findReply(@PathVariable Long review_id,
										   @PathVariable Long reply_id) {
		Reply reply = null;
		return ResponseEntity.ok(reply);
	}
	
	// 리플 목록
	@GetMapping("{review_id}")
	public ResponseEntity<List<ReplyDto>> findReplies(@AuthenticationPrincipal UserInfo userInfo,
												   	  @PathVariable Long review_id) {
		List<Reply> replies = replyMapper.findReplies(review_id);

	    List<ReplyDto> replyDtos = new ArrayList<>();
	    if (replies != null && replies.size() > 0) {
	        for (Reply reply : replies) {
	            ReplyDto replyDto = Reply.toDto(reply);
	            // 로그인하지 않은 사용자일 경우 writer를 false로 설정합니다.
	            if (userInfo == null || !reply.getMember_id().equals(userInfo.getUsername())) {
	                replyDto.setWriter(false);
	            } else {
	                replyDto.setWriter(true);
	            }
	            replyDtos.add(replyDto);
	        }
	    }

	    return ResponseEntity.ok(replyDtos);
	}
		
	// 리플 수정
	@PutMapping("{review_id}/{reply_id}")
	public ResponseEntity<Reply> updateReply(@AuthenticationPrincipal UserInfo userInfo,
											 @PathVariable Long reply_id,
											 @ModelAttribute Reply reply) {
		// 수정 권한 확인
        Reply findReply = replyMapper.findReply(reply_id);
        if (findReply.getMember_id().equals(userInfo.getUsername())) {
            replyMapper.updateReply(reply);
        }
		
		return ResponseEntity.ok(reply);
	}
	
	// 리플 삭제
	@DeleteMapping("{review_id}/{reply_id}")
	public ResponseEntity<String> removeReply(@AuthenticationPrincipal UserInfo userInfo,
											  @PathVariable Long reply_id) {
		 // 삭제 권한 확인
        Reply findReply = replyMapper.findReply(reply_id);
        if (findReply.getMember_id().equals(userInfo.getUsername())) {
            replyMapper.removeReply(reply_id);
        }
		return ResponseEntity.ok("삭제 성공");
	}
}











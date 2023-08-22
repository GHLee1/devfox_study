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

import com.devfox.board.config.UserInfo;
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
	
	// コメントを登録
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
	
	// リップル読み取り
	@GetMapping("{review_id}/{reply_id}")
	public ResponseEntity<Reply> findReply(@PathVariable Long review_id,
										   @PathVariable Long reply_id) {
		Reply reply = null;
		return ResponseEntity.ok(reply);
	}
	
	// リップのルリスト
	@GetMapping("{review_id}")
	public ResponseEntity<List<ReplyDto>> findReplies(@AuthenticationPrincipal UserInfo userInfo,
												   	  @PathVariable Long review_id) {
		List<Reply> replies = replyMapper.findReplies(review_id);

	    List<ReplyDto> replyDtos = new ArrayList<>();
	    if (replies != null && replies.size() > 0) {
	        for (Reply reply : replies) {
	            ReplyDto replyDto = Reply.toDto(reply);
	            // ログインしていないユーザーの場合はwriterをfalseに設定します。
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
		
	// リップルの修正
	@PutMapping("{review_id}/{reply_id}")
	public ResponseEntity<Reply> updateReply(@AuthenticationPrincipal UserInfo userInfo,
											 @PathVariable Long reply_id,
											 @ModelAttribute Reply reply) {
		// 修正権限の確認
        Reply findReply = replyMapper.findReply(reply_id);
        if (findReply.getMember_id().equals(userInfo.getUsername())) {
            replyMapper.updateReply(reply);
        }
		
		return ResponseEntity.ok(reply);
	}
	
	// リップルの削除
	@DeleteMapping("{review_id}/{reply_id}")
	public ResponseEntity<String> removeReply(@AuthenticationPrincipal UserInfo userInfo,
											  @PathVariable Long reply_id) {
		 // 削除権限の確認
        Reply findReply = replyMapper.findReply(reply_id);
        if (findReply.getMember_id().equals(userInfo.getUsername())) {
            replyMapper.removeReply(reply_id);
        }
		return ResponseEntity.ok("삭제 성공");
	}
}











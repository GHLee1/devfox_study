package com.devfox.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devfox.board.config.UserInfo;
import com.devfox.board.model.board.Board;
import com.devfox.board.model.board.BoardUpdateForm;
import com.devfox.board.model.board.BoardWriteForm;
import com.devfox.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("board")
@Controller
public class BoardController {
	//　依存性注入
	private final BoardService boardService;
	
	//　書き込みページせんい
    @GetMapping("write")
    public String writeForm(Model model,RedirectAttributes redirectAttributes) {
        model.addAttribute("writeForm", new BoardWriteForm());
        return "board/write";
    }
  

    // 掲示板の書き
    @PostMapping("write")
    public String write(@AuthenticationPrincipal UserInfo userInfo,
                        @Validated @ModelAttribute("writeForm") BoardWriteForm boardWriteForm,
                        BindingResult result,Model model,RedirectAttributes redirectAttributes) {
    	//　ログインがしなければホームパージをリタン
    	if(userInfo == null) {
    		return "redirect:/";
    	}
        
        // タイトルと内容の空白チェック
        if (boardWriteForm.getTitle()==null) {
            // バリデーションにエラーメッセージを追加します。
            result.rejectValue("title", "NotEmpty");
            result.rejectValue("contents", "NotEmpty");
            return "board/write"; 
        }
      
        // パラメータで受け取ったBoardWriteFormオブジェクトをBoard型に変換します。
        Board board = BoardWriteForm.toBoard(boardWriteForm);
        // ボードのオブジェクトにログインしたユーザーのIDを追加します。
        board.setMember_id(userInfo.getUsername());
        // ボードのオブジェクトを保存します。
        boardService.saveboard(board);
        // ホームページにリダイレクトします。
        return "redirect:/";
    }

   
    // 掲示板を読み
    @GetMapping("read")
    public String read(@RequestParam Long board_id,
                       Model model,
                       @AuthenticationPrincipal UserInfo userInfo
                       ) {
    	
        // ボードのアイディーに該当する掲示板をデータベースから探します
        Board board = boardService.readboard(board_id);
        // ボードのアイディーに該当する掲示板がない場合は、ホームページにリダイレクトします。
        if (board == null) {
            log.info("게시글 없음");
            return "redirect:/";
        }        
        // 投稿データオブジェクトを保管
        model.addAttribute("board", board);
        model.addAttribute("loginMember",userInfo);
        return "board/read";
    }

    // 投稿の修正ページへ移動
    @GetMapping("update")
    public String updateForm(@AuthenticationPrincipal UserInfo userInfo,
                             @RequestParam Long board_id,
                             Model model) {
        log.info("board_id: {}", board_id);

        /* ボードのアイディーに該当するスレッドがないか、スレッドの作成者が 
         * ログインしたユーザーのアイディーと違う場合は、
         * 修正せずにホームページにリダイレクトします。*/
        Board board = boardService.findboard(board_id);
        if (board_id == null || !board.getMember_id().equals(userInfo.getUsername())) {
            log.info("수정 권한 없음");
            return "redirect:/";
        }
        
        // 投稿データオブジェクトを保管
        model.addAttribute("board", Board.toBoardUpdateForm(board));
        return "board/update";
    }

    // 投稿の修正
    @PostMapping("update")
    public String update(@AuthenticationPrincipal UserInfo userInfo,
                         @RequestParam Long board_id,
                         @Validated @ModelAttribute("board") BoardUpdateForm updateboard,
                         BindingResult result) {
                         
        //  バリデーションにエラーがある場合、アップデートページに戻ります。
        if (result.hasErrors()) {
            return "board/update";
        }

        // ボードのアイディーに該当するボードの情報をデータベースから取得します。
        Board board = boardService.findboard(board_id);
        /* ボードのオブジェクトがなかったり、作成者がログインしたユーザーのアイディーと違う場合は、
        　修正せずにリストへリダイレクトします。*/
        if (board == null || !board.getMember_id().equals(userInfo.getUsername())) {
            log.info("수정 권한 없음");
            return "redirect:/";
        }
        // タイトルをを修正。
        board.setTitle(updateboard.getTitle());
        // 内容を修正。
        board.setContents(updateboard.getContents());
        
        // 修正したボードをデータベースに修正する。
        boardService.updateboard(board);
        return "redirect:/";
    }

    // 投稿の削除
    @GetMapping("delete")
    public String remove(@AuthenticationPrincipal UserInfo userInfo,
                         @RequestParam Long board_id) {
        // ボードのアイディーに該当する投稿を取得します。
        Board board = boardService.findboard(board_id);
        // ボードが存在しない場合や、作成者とログインユーザーのアイディが異なる場合は、ホームページにリダイレクトします。
        if (board == null || !board.getMember_id().equals(userInfo.getUsername())) {
            log.info("삭제 권한 없음");
            return "redirect:/";
        }
    	// 投稿を削除します。
        boardService.removeboard(board_id);
        // ホームのパージにリダイレクトします。
        return "redirect:/";
    }
    

   
    @GetMapping("myboardList")
    public String myBoardList(
    					@AuthenticationPrincipal UserInfo userInfo
                        ,Model model) {
    	List<Board> boards = boardService.findBoardsByMemberId(userInfo.getUsername());
    	if(userInfo !=null) {
    		model.addAttribute("boards", boards);
    		model.addAttribute("loginMember",userInfo);
    	}
    	return "member/myboardList";
    }
    
    

	
}

package com.devfox.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devfox.board.config.UserInfo;
import com.devfox.board.model.board.Board;
import com.devfox.board.model.member.LoginForm;
import com.devfox.board.model.member.MemberJoinForm;
import com.devfox.board.repository.MemberMapper;
import com.devfox.board.service.BoardService;
import com.devfox.board.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private final BoardService boardService;
	
	
	 // 会員登録ページへ移動
    @GetMapping("join")
    public String joinForm(Model model) {
        model.addAttribute("joinForm", new MemberJoinForm());
        return "member/joinForm";
    }
    // 会員登録
    @PostMapping("join")
    public String join(@Validated @ModelAttribute("joinForm") MemberJoinForm joinForm,
                       BindingResult result,Model model, RedirectAttributes redirectAttributes) {
        log.info("joinForm: {}", joinForm);

        // バリデーションにエラーがある場合、会員登録パージをリダイレクト。
        if (result.hasErrors()) {
            return "member/joinForm";
        }
        memberService.saveMember(MemberJoinForm.toMember(joinForm));
        return "redirect:/member/login";
    }
    
    
    // ログインページ
    @GetMapping("login")
    public String loginForm(@RequestParam(value="error", required = false) String error,
    						@RequestParam(value="exception",required = false) String exception
    						,Model model) {

        model.addAttribute("loginForm", new LoginForm());
        /* エラーと例外をモデルに入れてview resolve */
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		//log.info("error {}",exception);
        return "member/loginForm";
    }
    //　セキュリティがログインを確認
    @GetMapping("login-success")
    public String loginSuccess(@AuthenticationPrincipal UserInfo userInfo) {
    	log.info("로그인성공");
    	return "redirect:/";
    }
    //　セキュリティがログインを確認
    @GetMapping("login-failed")
    public String loginFailed() {
    	log.info("로그인실패");
    	return "redirect:/";
    }
    //  私のページに移動
    @GetMapping("myPage")
    public String myPage(Model model,@AuthenticationPrincipal UserInfo userInfo) {
    	if(userInfo == null) {
    		return "redirect:/";
    	}
    	List<Board> boards = boardService.findBoardsByMemberId(userInfo.getUsername());
    	if(userInfo !=null) {
    		model.addAttribute("boards", boards);
    		model.addAttribute("loginMember",userInfo);
    	}
        return "member/mypage"  ;
    }
    
   
    
    //重複チェック
    @ResponseBody
    @PostMapping("idCheck")
    public Map<Object, Object> idcheck(@RequestBody String member_id) {
        int count = 0;
        
        Map<Object, Object> map = new HashMap<Object, Object>();
        System.out.println(map);
        count = memberMapper.idCheck(member_id);
        map.put("cnt", count);
 
        return map;
    }
    
    
    
	
}

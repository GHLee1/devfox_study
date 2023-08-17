package com.devfox.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devfox.board.config.UserInfo;
import com.devfox.board.model.member.LoginForm;
import com.devfox.board.model.member.Member;
import com.devfox.board.model.member.MemberJoinForm;
import com.devfox.board.repository.MemberMapper;
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
	
	 // 회원가입 페이지 이동
    @GetMapping("join")
    public String joinForm(Model model) {
        // joinForm.html 의 필드 세팅을 위해 model 에 빈 MemberJoinForm 객체 생성하여 저장한다
        model.addAttribute("joinForm", new MemberJoinForm());
        // member/joinForm.html 페이지를 리턴한다.
        return "member/joinForm";
    }
    // 회원가입
    @PostMapping("join")
    public String join(@Validated @ModelAttribute("joinForm") MemberJoinForm joinForm,
                       BindingResult result,Model model, RedirectAttributes redirectAttributes) {
        log.info("joinForm: {}", joinForm);

        // validation 에 에러가 있으면 가입시키지 않고 member/joinForm.html 페이지로 돌아간다.
        if (result.hasErrors()) {
            return "member/joinForm";
        }
        
        memberService.saveMember(MemberJoinForm.toMember(joinForm));
        return "redirect:/member/login";
    }
    
    
    // 로그인 페이지 이동
    @GetMapping("login")
    public String loginForm(@RequestParam(value="error", required = false) String error,
    						@RequestParam(value="exception",required = false) String exception
    						,Model model) {
        // member/loginForm.html 에 필드 셋팅을 위해 빈 LoginForm 객체를 생성하여 model 에 저장한다.
        model.addAttribute("loginForm", new LoginForm());
        /* 에러와 예외를 모델에 담아 view resolve */
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
        // member/loginForm.html 페이지를 리턴한다.
		//log.info("error {}",exception);
        return "member/loginForm";
    }
    
    

   
    
    @GetMapping("login-success")
    public String loginSuccess(@AuthenticationPrincipal UserInfo userInfo) {
    	log.info("로그인성공");
    	return "redirect:/";
    }
    
    @GetMapping("login-failed")
    public String loginFailed() {
    	log.info("로그인실패");
    	return "redirect:/";
    }
    
    @GetMapping("myPage")
    public String myPage(Model model,@SessionAttribute(value="loginMember",required = false) Member loginMember) {
    	model.addAttribute("loginMember",loginMember.getMember_id());
        return "member/mypage"  ;
    }
    
    @GetMapping("updateMember")
    public String updateMember(Model model,@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
    	
        model.addAttribute("loginMember", loginMember);
        return "member/updateMember";
    }
    
    @PostMapping("updateMember")
    public ResponseEntity<String> updateMember2(Model model
    		,@RequestParam String name, @RequestParam String password, @RequestParam String phone_number
    		,@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
    	
    	loginMember.setPassword(password);
    	loginMember.setName(name);
    	
        memberMapper.updateMember(loginMember);
        
        return ResponseEntity.ok("변경성공");
    }
    
    //중복체크
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

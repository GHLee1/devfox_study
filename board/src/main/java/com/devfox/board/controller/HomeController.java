package com.devfox.board.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.devfox.board.config.UserInfo;
import com.devfox.board.model.board.Board;
import com.devfox.board.model.member.Member;
import com.devfox.board.service.BoardService;
import com.devfox.board.util.PageNavigator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	
private final BoardService boardService;
	
	final int countPerPage = 10;    // 페이지 당 글 수
    final int pagePerGroup = 5;     // 페이지 이동 그룹 당 표시할 페이지 수
	
    
    
	 // 게시글 전체 보기
    @GetMapping("/")
    public String home(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "searchText", defaultValue = "") String searchText,
                       Model model, @AuthenticationPrincipal UserInfo userInfo) {
    	log.info("searchText: {}", searchText);
    	int total = boardService.getTotal(searchText);

        PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);

        // 데이터베이스에 저장된 모든 Board 객체를 리스트 형태로 받는다.
        List<Board> boards = boardService.findboards(searchText,
        		navi.getStartRecord(), navi.getCountPerPage());
        log.info("boards : {}",boards);
        // Board 리스트를 model 에 저장한다.
        model.addAttribute("boards", boards);
        // PageNavigation 객체를 model 에 저장한다.
        model.addAttribute("navi", navi);
        model.addAttribute("searchText", searchText);
        
        model.addAttribute("loginMember", userInfo);
        
        // board/list.html 를 찾아서 리턴한다.
        return "main/index";
    }

}

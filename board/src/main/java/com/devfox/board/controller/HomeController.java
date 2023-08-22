package com.devfox.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devfox.board.config.UserInfo;
import com.devfox.board.model.board.Board;
import com.devfox.board.service.BoardService;
import com.devfox.board.util.PageNavigator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	
private final BoardService boardService;
	
	final int countPerPage = 10;    // ページ当たりの表示数は
    final int pagePerGroup = 5;     // ページ移動グループごとに表示するページ数
	
    
    
	 // ホームパージ
    @GetMapping("/")
    public String home(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "searchText", defaultValue = "") String searchText,
                       Model model, @AuthenticationPrincipal UserInfo userInfo) {
    	log.info("searchText: {}", searchText);
    	int total = boardService.getTotal(searchText);

        PageNavigator navi = 
        		new PageNavigator(countPerPage, pagePerGroup, page, total);

        // データベースに保存されている全てのボードのオブジェクトをリスト形式で受け取ります。
        List<Board> boards = boardService.findboards(searchText,
        		navi.getStartRecord(), navi.getCountPerPage());
        log.info("boards : {}",boards);
        // オブジェクトをモデルに保存します。
        model.addAttribute("boards", boards);
        model.addAttribute("navi", navi);
        model.addAttribute("searchText", searchText);
        model.addAttribute("loginMember", userInfo);
        return "main/index";
    }

}

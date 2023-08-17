package com.devfox.board.model.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class BoardWriteForm {
	  	@NotBlank(message = "제목을 입력해주세요.")
	    private String title;

	    @NotBlank(message = "내용을 입력해주세요.")
	    private String contents;

	    public static Board toBoard(BoardWriteForm boardWriteForm) {
    	Board board = new Board();
    	board.setTitle(boardWriteForm.getTitle());
    	board.setContents(boardWriteForm.getContents());
        return board;
	    }
}

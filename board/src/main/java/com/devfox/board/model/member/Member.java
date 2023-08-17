package com.devfox.board.model.member;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Member {
	//id
    private String member_id;
    //pw
    private String password;
    //name
    private String name;
}

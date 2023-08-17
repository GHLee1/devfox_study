package com.devfox.board.model.member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberUpdateForm {
    
	@Size(min = 5, max = 10)
    private String member_id;
    
    @Size(min = 5, max = 10)
    private String password;
    
    @NotBlank
    private String name;
    
  

    public static Member toMember(MemberUpdateForm memberUpdateForm) {
        Member member = new Member();
        member.setMember_id(memberUpdateForm.getMember_id());
        member.setPassword(memberUpdateForm.getPassword());
        member.setName(memberUpdateForm.getName());
        return member;
    }
}

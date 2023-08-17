package com.devfox.board.model.member;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class MemberJoinForm {
    
	@Size(min = 5, max = 20)
    private String member_id;
    
    @Size(min = 5, max = 20)
    private String password;
    
    @NotBlank
    private String name;
    
    public static Member toMember(MemberJoinForm memberJoinForm) {
        Member member = new Member();
        member.setMember_id(memberJoinForm.getMember_id());
        member.setPassword(memberJoinForm.getPassword());
        member.setName(memberJoinForm.getName());
        return member;
    }
}

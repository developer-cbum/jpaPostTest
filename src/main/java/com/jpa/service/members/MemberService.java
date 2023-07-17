package com.jpa.service.members;

import com.jpa.domain.MemberDTO;
import com.jpa.entity.Member;

import java.util.Optional;

public interface MemberService {

    public void join(MemberDTO memberDTO);

    public Optional<Long> login(String memberEmail, String memberPassword);

    public Optional<Long> checkId(String memberEmail);


    default Member toEntity(MemberDTO memberDTO){
        return Member.builder().id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberName(memberDTO.getMemberName())
                .build();
    }

}

package com.jpa.service.members;

import com.jpa.domain.MemberDTO;
import com.jpa.entity.Member;
import com.jpa.repository.members.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public void join(MemberDTO memberDTO) {
        memberRepository.save(toEntity(memberDTO));
    }

    @Override
    public Optional<Long> login(String memberEmail, String memberPassword) {
        return memberRepository.findIdByMemberEmailAndMemberPassword(memberEmail,memberPassword);
    }

    @Override
    public Optional<Long> checkId(String memberEmail) {
        return memberRepository.checkId(memberEmail);
    }
}

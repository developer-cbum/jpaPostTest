package com.jpa.entity;

import com.jpa.repository.MemberRepository;
import com.jpa.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class MemberRepositoryTests {

//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    public void saveTest(){
//        Member member = new Member();
//        member.setMemberName("이종문");
//        member.setMemberEmail("ljm1234@naver.com");
//        member.setMemberPassword("1234");
//        memberRepository.save(member);
//    }
//
//    @Test
//    public void loginTest(){
//        log.info(memberRepository.findIdByMemberEmailAndMemberPassword("ljm1234@naver.com","1234").toString());
//    }


}

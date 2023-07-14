package com.jpa.repository;

import com.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m.id  from Member m where m.memberEmail=:memberEmail and m.memberPassword=:memberPassword")
    public Optional<Long> findIdByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);

    @Query("select m.id  from Member m where m.memberEmail=:memberEmail")
    public Optional<Long> checkId(String memberEmail);

}

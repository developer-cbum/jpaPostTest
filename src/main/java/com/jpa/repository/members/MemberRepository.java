package com.jpa.repository.members;

import com.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberQueryDSL {

    @Query("select m.id  from Member m where m.memberEmail=:memberEmail and m.memberPassword=:memberPassword")
    public Optional<Long> findIdByMemberEmailAndMemberPassword(@Param("memberEmail") String memberEmail, @Param("memberPassword")String memberPassword);

    @Query("select m.id  from Member m where m.memberEmail=:memberEmail")
    public Optional<Long> checkId(String memberEmail);

}

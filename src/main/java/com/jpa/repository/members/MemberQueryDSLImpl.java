package com.jpa.repository.members;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberQueryDSLImpl implements MemberQueryDSL {

    @Autowired
    private JPAQueryFactory query;
}

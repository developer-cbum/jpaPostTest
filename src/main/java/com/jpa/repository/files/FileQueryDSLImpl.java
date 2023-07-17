package com.jpa.repository.files;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FileQueryDSLImpl implements FileQueryDSL {
    @Autowired
    private JPAQueryFactory query;
}

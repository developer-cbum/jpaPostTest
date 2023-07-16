package com.jpa.repository;

import com.jpa.entity.Post;
import com.jpa.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.jpa.entity.QPost.post;

@Slf4j
public class PostQueryDSLImpl implements PostQueryDSL {

    @Autowired
    private JPAQueryFactory query;


    @Override
    public Page<Post> findAllWithPaging(Pageable pageable) {
        List<Post> posts = query.selectFrom(post).orderBy(post.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

        Long count = query.select(post.count()).from(post).fetchOne();

        return new PageImpl<>(posts,pageable,count);
    }
}

package com.jpa.repository.posts;

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
        List<Post> posts = query.select(post).from(post).join(post.member).fetchJoin().orderBy(post.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

        Long count = query.select(post.count()).from(post).fetchOne();

        return new PageImpl<>(posts,pageable,count);
    }

    @Override
    public Post findPostById(Long id) {
        return query.select(post).from(post).leftJoin(post.files).fetchJoin().join(post.member).fetchJoin().where(post.id.eq(id)).fetchOne();
    }

    @Override
    public void update(Post post) {
        query.update(QPost.post).set(QPost.post.postTitle, post.getPostTitle())
                .set(QPost.post.postContent, post.getPostContent())
                .where(QPost.post.id.eq(post.getId()))
                .execute();
    }
}

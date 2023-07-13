package com.jpa.entity;

import com.jpa.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;


    @Test
    public void saveTest(){
        for (int i = 0; i < 100; i++) {
            Post post = new Post();
            post.setPostTitle("제목2" +(i+1));
            post.setPostContent("내용2"+(i+1));
            postRepository.save(post);
        }
    }


    @Test
    public void findByIdTest(){
        postRepository.findById(1L).ifPresent(post -> log.info(post.toString()));
    }

    @Test
    public void findAllTest(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        final Page<Post> pagingOfPost = postRepository.findAll(pageRequest);
        pagingOfPost.get().forEach(post -> log.info(post.toString()));

    }


    @Test
    public void updateTest(){
        postRepository.findById(1L).ifPresent(post -> post.setPostContent("수정됌"));
    }

    @Test
    public void removeTest(){
        Optional<Post> foundPost = postRepository.findById(1L);
        foundPost.ifPresent(post -> postRepository.delete(post));
    }


}

package com.jpa.entity;

import com.jpa.repository.members.MemberRepository;
import com.jpa.repository.posts.PostRepository;
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
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

//
//    @Test
//    public void saveTest(){
//
//        Member member= memberRepository.findById(1L).get();
//
//        for (int i = 0; i < 100; i++) {
//            Post post = new Post();
//            post.setPostTitle("제목" +(i+1));
//            post.setPostContent("내용"+(i+1));
//            post.setMember(member);
//            postRepository.save(post);
//        }
//    }

//    @Test
//    public void findMemberAndPostTest(){
//        log.info(postRepository.findAll().toString());
//    }
//
//
//    @Test
//    public void findByIdTest(){
//        postRepository.findById(1L).ifPresent(post -> log.info(post.toString()));
//    }
//
//    @Test
//    public void findAllTest(){
//        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
//        final Page<Post> pagingOfPost = postRepository.findAll(pageRequest);
//        pagingOfPost.getContent().get(0).getMember();
//        pagingOfPost.get().forEach(post -> log.info(post.toString()));
//
//    }
//
//
//    @Test
//    public void updateTest(){
//        postRepository.findById(1L).ifPresent(post -> post.setPostContent("수정됌"));
//    }
//
//    @Test
//    public void removeTest(){
//        Optional<Post> foundPost = postRepository.findById(1L);
//        foundPost.ifPresent(post -> postRepository.delete(post));
//    }


}

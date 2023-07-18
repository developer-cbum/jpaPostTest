package com.jpa.entity;

import com.jpa.domain.PostDTO;
import com.jpa.repository.files.FileRepository;
import com.jpa.repository.members.MemberRepository;
import com.jpa.repository.posts.PostRepository;
import com.jpa.service.posts.PostService;
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
public class FileRepositoryTests {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;

//    @Test
//    public void saveTest(){
//        Member member = memberRepository.findById(1L).get();
//        File file = new File();
//        Post post = new Post();
//        post.setPostContent("야호");
//        post.setPostTitle("옙");
//        post.setMember(member);
//
//        file.setFileName("대충");
//        file.setFilePath("넣어");
//        file.setFileUuid("대충");
//        file.setFileSize(10L);
//        file.setPost(post);
//
//
//
//    }
}

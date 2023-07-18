package com.jpa.service.posts;

import com.jpa.domain.FileDTO;
import com.jpa.domain.PostDTO;
import com.jpa.entity.File;
import com.jpa.entity.Member;
import com.jpa.entity.Post;
import com.jpa.exception.NoPostException;
import com.jpa.repository.files.FileRepository;
import com.jpa.repository.members.MemberRepository;
import com.jpa.repository.posts.PostRepository;
import com.jpa.service.files.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class
PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public Page<Post> getList(Pageable pageable) {
        return postRepository.findAllWithPaging(pageable);
    }

    @Override
    public long getTotal() {
        return postRepository.count();
    }

    @Transactional
    @Override
    public void register(PostDTO postDTO) {
        // id를 변수에 담음과 동시에 save 된다.
        Long id = postRepository.save(toEntity(postDTO)).getId();

        postRepository.findById(id).ifPresent(post -> {
            for (int i = 0; i < postDTO.getFiles().size(); i++) {
                postDTO.getFiles().get(i).setPost(post);
                fileRepository.save(toEntity(postDTO.getFiles().get(i)));
            }
        });
    }

    @Transactional
    @Override
    public void modify(PostDTO postDTO) {
        // setter가 없어 빌더 이용
        // dto에 직접 member를 넣어준다.
        Optional<Member> foundMember = memberRepository.findById((Long) session.getAttribute("id"));
        foundMember.ifPresent(member -> postDTO.setMember(member));
        Long id = postRepository.save(toEntity(postDTO)).getId();
        postRepository.findById(id).ifPresent(post -> {
            for (int i = 0; i < postDTO.getFiles().size(); i++) {
                postDTO.getFiles().get(i).setPost(post);
                fileRepository.save(toEntity(postDTO.getFiles().get(i)));
            }
        });
        postDTO.getFileIdsForDelete().forEach(fileRepository::deleteById);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }



    @Override
    public void remove(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new NoPostException("게시물 없음");
        });

        post.getFiles().stream().map(File::getId).forEach(fileRepository::deleteById);
        postRepository.deleteById(id);
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        return Optional.ofNullable(postRepository.findPostById(id));
    }
}

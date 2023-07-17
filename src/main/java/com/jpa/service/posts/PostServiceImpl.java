package com.jpa.service.posts;

import com.jpa.domain.PostDTO;
import com.jpa.entity.Post;
import com.jpa.repository.files.FileRepository;
import com.jpa.repository.posts.PostRepository;
import com.jpa.service.files.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final FileRepository fileRepository;

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
        postRepository.save(toEntity(postDTO));
        for (int i = 0; i < postDTO.getFiles().size(); i++) {
            fileRepository.save(toEntity(postDTO.getFiles().get(i)));
        }
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }
}

package com.jpa.service.posts;

import com.jpa.domain.PostDTO;
import com.jpa.entity.Post;
import com.jpa.repository.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Page<Post> getList(Pageable pageable) {
        return postRepository.findAllWithPaging(pageable);
    }

    @Override
    public long getTotal() {
        return postRepository.count();
    }

    @Override
    public void register(PostDTO postDTO) {
        postRepository.save(toEntity(postDTO));
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

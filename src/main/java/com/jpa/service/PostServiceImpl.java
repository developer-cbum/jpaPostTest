package com.jpa.service;

import com.jpa.entity.Post;
import com.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}

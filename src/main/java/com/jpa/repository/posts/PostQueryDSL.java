package com.jpa.repository.posts;

import com.jpa.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface PostQueryDSL {

    public Page<Post> findAllWithPaging(Pageable pageable);

    public Post findPostById(Long id);



}

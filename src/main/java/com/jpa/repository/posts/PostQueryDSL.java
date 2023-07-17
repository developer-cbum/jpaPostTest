package com.jpa.repository.posts;

import com.jpa.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public interface PostQueryDSL {

    public Page<Post> findAllWithPaging(Pageable pageable);



}

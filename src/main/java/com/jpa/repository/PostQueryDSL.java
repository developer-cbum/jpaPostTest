package com.jpa.repository;

import com.jpa.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryDSL {

    public Page<Post> findAllWithPaging(Pageable pageable);

}

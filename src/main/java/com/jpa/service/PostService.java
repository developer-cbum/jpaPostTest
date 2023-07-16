package com.jpa.service;

import com.jpa.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    public Page<Post> getList(Pageable pageable);

}

package com.jpa.repository;

import com.jpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Post  p set p.postTitle = :content, p.postContent = :title, p.updatedDate = current_timestamp where p.id = :id")
    public void updatePost(@Param("content") String content, @Param("title")String title, @Param("id")Long id);
}

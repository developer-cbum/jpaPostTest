package com.jpa.repository.posts;

import com.jpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostQueryDSL {

//    @Query("select p from Post p join fetch p.files f join fetch p.member m where p.id = :id")
//    public Optional<Post> findById(@Param("id") Long id);

}

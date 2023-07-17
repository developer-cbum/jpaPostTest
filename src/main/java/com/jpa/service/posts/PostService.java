package com.jpa.service.posts;

import com.jpa.domain.FileDTO;
import com.jpa.domain.PostDTO;
import com.jpa.entity.File;
import com.jpa.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostService {

    public Page<Post> getList(Pageable pageable);

    public long getTotal();

    public void register(PostDTO postDTO);

    public Optional<Post> findById(Long id);

    public void remove(Long id);

    default Post toEntity(PostDTO postDTO){
            return Post.builder().id(postDTO.getId())
                    .postTitle(postDTO.getPostTitle())
                    .postContent(postDTO.getPostTitle())
                    .member(postDTO.getMember())
                    .build();
    }

    default File toEntity(FileDTO fileDTO){
        return File.builder().id(fileDTO.getId())
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileUuid(fileDTO.getFileUuid())
                .fileSize(fileDTO.getFileSize())
                .post(fileDTO.getPost())
                .build();

    }



}

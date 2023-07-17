package com.jpa.domain;

import com.jpa.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Data
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileUuid;
    private String fileSize;
    private Post post;
}

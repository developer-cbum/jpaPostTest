package com.jpa.domain;

import com.jpa.entity.File;
import com.jpa.entity.Post;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter @Setter @ToString(exclude = "post")
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileUuid;
    private String fileSize;
    private Post post;
}

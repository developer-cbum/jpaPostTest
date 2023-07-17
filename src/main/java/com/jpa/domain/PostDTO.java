package com.jpa.domain;

import com.jpa.entity.File;
import com.jpa.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    boolean deleted = Boolean.FALSE;
    private Member member;
    private List<FileDTO> files = new ArrayList<>();
    private List<Long> fileIdsForDelete = new ArrayList<>();
}

package com.jpa.entity;

import com.jpa.auditing.Period;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_file")
@Getter @ToString(exclude = "post")
@SQLDelete(sql ="update tbl_file set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends Period {
    @Comment("파일 고유번호")
    @Id @GeneratedValue @EqualsAndHashCode.Include
    private Long id;
    @Comment("파일 이름")
    @NotNull private String fileName;
    @Comment("파일 경로")
    @NotNull private String filePath;
    @Comment("파일 uuid")
    @NotNull private String fileUuid;
    @Comment(value = "파일 크기")
    @NotNull private Long fileSize;
    @Comment( value = "파일 삭제여부")
    @NotNull boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private Post post;

    @Builder
    public File(Long id, String fileName, String filePath, String fileUuid, Long fileSize, Post post) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.post = post;
    }
}

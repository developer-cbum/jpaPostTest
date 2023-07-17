package com.jpa.entity;

import com.jpa.auditing.Period;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_file")
@Getter @Setter @ToString(exclude = "post")
@SQLDelete(sql ="update tbl_file set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
@NoArgsConstructor
public class File extends Period {
    @Id @GeneratedValue @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String fileName;
    @NotNull private String filePath;
    @NotNull private String fileUuid;
    @NotNull private String fileSize;
    @NotNull boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Builder
    public File(Long id, String fileName, String filePath, String fileUuid, String fileSize, Post post) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.post = post;
    }
}

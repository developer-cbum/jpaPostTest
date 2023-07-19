package com.jpa.auditing;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class Period {
    @CreatedDate
    @Comment("생성일")
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Comment("수정일 혹은 삭제일")
    private LocalDateTime updatedDate;



}
























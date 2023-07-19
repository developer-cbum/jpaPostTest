package com.jpa.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jpa.auditing.Period;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString(exclude = "member")
@Table(name= "tbl_post")
@SQLDelete(sql ="update tbl_post set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String postTitle;
    @NotNull private String postContent;
    @NotNull boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<File> files = new ArrayList<>();



    @Builder
    public Post(Long id, String postTitle, String postContent, Member member) {
        this.id = id;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.member = member;
    }
}

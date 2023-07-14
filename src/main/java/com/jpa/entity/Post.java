package com.jpa.entity;

import com.fasterxml.jackson.annotation.*;
import com.jpa.auditing.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter @ToString(exclude = "member")
@Table(name= "tbl_post")
@SQLDelete(sql ="update tbl_post set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
public class Post extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String postTitle;
    @NotNull private String postContent;
    @NotNull boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}

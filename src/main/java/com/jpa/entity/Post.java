package com.jpa.entity;

import com.jpa.auditing.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter @ToString
@Table(name= "tbl_post")
@SQLDelete(sql ="update tbl_post set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Post extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String postTitle;
    @NotNull private String postContent;
    @NotNull boolean deleted = Boolean.FALSE;
}

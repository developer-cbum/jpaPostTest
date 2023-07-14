package com.jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "tbl_member")
@SQLDelete(sql ="update tbl_member set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Member {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String memberName;
    @Column(unique = true) @NotNull private String memberEmail;
    @NotNull private String memberPassword;
    @NotNull boolean deleted = Boolean.FALSE;
}

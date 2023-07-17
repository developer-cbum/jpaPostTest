package com.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
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
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String memberName;
    @Column(unique = true) @NotNull private String memberEmail;
    @NotNull private String memberPassword;
    @NotNull boolean deleted = Boolean.FALSE;

    @Builder
    public Member(Long id, String memberName, String memberEmail, String memberPassword) {
        this.id = id;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }
}

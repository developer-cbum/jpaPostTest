package com.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpa.auditing.Period;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "tbl_member")
@SQLDelete(sql ="update tbl_member set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Period {
    @Comment("회원 고유번호")
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Comment("회원 이름")
    @NotNull private String memberName;
    @Comment("회원 이메일")
    @Column(unique = true) @NotNull private String memberEmail;
    @Comment("회원 비밀번호")
    @NotNull private String memberPassword;
    @Comment("회원 삭제여부")
    @NotNull boolean deleted = Boolean.FALSE;

    @Builder
    public Member(Long id, String memberName, String memberEmail, String memberPassword) {
        this.id = id;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }
}

package com.jpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    boolean deleted = Boolean.FALSE;
}

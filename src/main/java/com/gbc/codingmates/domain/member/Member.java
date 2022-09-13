package com.gbc.codingmates.domain.member;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbc.codingmates.domain.BaseTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Your id:")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$", message = "3~12 in length, with no special characters")
    @Column(length = 15, nullable = false)
    @JsonIgnore
    private String username;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    @JsonIgnore
    private String password;

//    @NotNull
//    @Size(min = 3, max= 20)
//    private String nickname;

    @Enumerated(STRING)
    private MemberStatus status;

    @Embedded
    private OAuthEmail oAuthEmail = new OAuthEmail();

    @Embedded
    private Resume resume = new Resume();

    @Builder
    public Member(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Builder
    public Member(String username, String email, String password, MemberStatus status,
        OAuthEmail oAuthEmail, Resume resume) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.oAuthEmail = oAuthEmail;
        this.resume = resume;
    }
}

package com.gbc.codingmates.domain.bookmark;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.dto.member.MemberDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @JoinColumn(name = "member_id")
    private Long member_id;

    @Column(nullable = false)
    private Boolean accept_info;

    public Bookmark(Project project, MemberDto memberDto){
        this.project = project;
        this.member_id = memberDto.getMemberId();
    }
}

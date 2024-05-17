package com.searchsquare.member.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String nickname;
    private String email;
    private String profileImg;
    private String gender;
    private String birthYear;
    private String age;
    private Provider provider;
}

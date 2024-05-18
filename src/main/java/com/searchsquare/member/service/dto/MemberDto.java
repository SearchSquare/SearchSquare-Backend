package com.searchsquare.member.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private int id;
    private String nickname;
    private String email;
    private String profileImg;
    private String gender;
    private int birthYear;
    private String age;
    private Provider provider;
    private AccessTokenDto accessToken;
}

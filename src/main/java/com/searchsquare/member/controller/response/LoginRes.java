package com.searchsquare.member.controller.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRes {

    private String nickname;
    private String email;
    private String profileImg;
    private String accessToken;
}

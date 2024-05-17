package com.searchsquare.member.service.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NaverLoginCommand {

    private String accessToken;
}

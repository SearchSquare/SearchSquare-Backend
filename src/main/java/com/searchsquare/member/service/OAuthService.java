package com.searchsquare.member.service;

import com.searchsquare.member.service.command.NaverLoginCommand;
import com.searchsquare.member.service.dto.MemberDto;

public interface OAuthService {

    MemberDto naverLogin(NaverLoginCommand cmd);
}

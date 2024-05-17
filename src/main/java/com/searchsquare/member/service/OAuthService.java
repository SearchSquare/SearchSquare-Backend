package com.searchsquare.member.service;

import com.searchsquare.member.controller.response.LoginRes;
import com.searchsquare.member.service.command.NaverLoginCommand;

public interface OAuthService {

    LoginRes naverLogin(NaverLoginCommand cmd);
}

package com.searchsquare.member.controller;

import com.searchsquare.core.response.BaseResponse;
import com.searchsquare.member.controller.request.NaverLoginReq;
import com.searchsquare.member.controller.response.LoginRes;
import com.searchsquare.member.service.OAuthService;
import com.searchsquare.member.service.command.NaverLoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final OAuthService oauthService;

    @PostMapping("/oauth/naver")
    public ResponseEntity<BaseResponse<LoginRes>> naverLogin(@RequestBody NaverLoginReq req) {
        LoginRes res = oauthService.naverLogin(
            NaverLoginCommand.builder().accessToken(req.getAccessToken()).build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}

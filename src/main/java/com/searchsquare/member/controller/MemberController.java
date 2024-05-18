package com.searchsquare.member.controller;

import com.searchsquare.core.response.BaseResponse;
import com.searchsquare.member.controller.request.NaverLoginReq;
import com.searchsquare.member.service.MemberService;
import com.searchsquare.member.service.command.NaverLoginCommand;
import com.searchsquare.member.service.dto.MemberDto;
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

    private final MemberService memberService;

    /**
     * 네이버 인증 코드를 사용하여 사용자 정보를 조회한 후 로그인 또는 회원가입을 처리한다. JWT 토큰을 자체적으로 생성하여 응답으로 반환한다.
     *
     * @param req 네이버 로그인 성공 시 발급받는 code
     * @return 회원 정보와 서비스 자체 AccessToken
     */
    @PostMapping("/oauth/naver")
    public ResponseEntity<BaseResponse<MemberDto>> naverLogin(@RequestBody NaverLoginReq req) {
        MemberDto res = memberService.naverLogin(
            NaverLoginCommand.builder().accessToken(req.getAccessToken()).build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}

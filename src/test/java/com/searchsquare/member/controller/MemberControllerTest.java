package com.searchsquare.member.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchsquare.MockMvcTest;
import com.searchsquare.member.controller.request.NaverLoginReq;
import com.searchsquare.member.service.MemberService;
import com.searchsquare.member.service.command.NaverLoginCommand;
import com.searchsquare.member.service.dto.AccessTokenDto;
import com.searchsquare.member.service.dto.MemberDto;
import com.searchsquare.member.service.dto.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class MemberControllerTest extends MockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;  // MemberServic

    @DisplayName("네이버 회원 정보를 바탕으로 회원가입 후 JWT 토큰을 반환한다.")
    @Test
    void joinSuccess() throws Exception {
        //given
        String url = "/members/oauth/naver";
        NaverLoginReq req = NaverLoginReq.builder()
            .accessToken("code-from-naver")
            .build();
        MemberDto res = MemberDto.builder()
            .id(1234)
            .nickname("nickname")
            .email("mock@domain.com")
            .profileImg("mockURL")
            .gender("M")
            .birthYear(2024)
            .age("20-29")
            .provider(Provider.NAVER)
            .accessToken(AccessTokenDto.builder()
                .accessToken("mock-access-token")
                .build())
            .build();

        // when
        when(memberService.naverLogin(any(NaverLoginCommand.class))).thenReturn(res);

        // then
        mockMvc.perform(
                post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req))
            ).andDo(print())
            .andExpect(status().isOk());
    }

}
package com.searchsquare.member.service;

import static java.util.Objects.isNull;

import com.searchsquare.infra.oauth.NaverApiClient;
import com.searchsquare.infra.oauth.response.NaverLoginRes;
import com.searchsquare.member.controller.response.LoginRes;
import com.searchsquare.member.repository.MemberRepository;
import com.searchsquare.member.service.command.NaverLoginCommand;
import com.searchsquare.member.service.dto.ExistMemberSearch;
import com.searchsquare.member.service.dto.MemberDto;
import com.searchsquare.member.service.dto.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuthServiceImpl implements OAuthService {

    private static final String DEFAULT_PROFILE_IMG = "https://user-images.githubusercontent.com/6022883/45476027-e45c1a80-b778-11e8-9716-4e39c6d6e58e.png";
    private final NaverApiClient naverApiClient;
    private final MemberRepository memberRepository;

    @Override
    public LoginRes naverLogin(NaverLoginCommand cmd) {
        NaverLoginRes naverLoginRes = naverApiClient.requestOauthInfo(cmd.getAccessToken());
        System.out.println("naverLoginRes = " + naverLoginRes);
        MemberDto member = memberRepository.findExistingMember(
            ExistMemberSearch.builder()
                .email(naverLoginRes.getEmail())
                .provider(naverLoginRes.getProvider())
                .build()
        );
        if (isNull(member)) {
            member = createNewMember(naverLoginRes);
            memberRepository.save(member);
        }
        return LoginRes.builder()
            .nickname(member.getNickname())
            .email(member.getEmail())
            .profileImg(member.getProfileImg())
            .accessToken("JWT 토큰")
            .build();
    }

    private MemberDto createNewMember(NaverLoginRes naverLoginRes) {
        return MemberDto.builder()
            .email(naverLoginRes.getEmail())
            .age(naverLoginRes.getAge())
            .birthYear(naverLoginRes.getBirthYear())
            .gender(naverLoginRes.getGender())
            .nickname(naverLoginRes.getNickname())
            .profileImg(checkImg(naverLoginRes.getProfileImg()))
            .provider(Provider.NAVER)
            .build();
    }

    private String checkImg(String profileImg) {
        return profileImg == null
            ? DEFAULT_PROFILE_IMG
            : profileImg;
    }
}

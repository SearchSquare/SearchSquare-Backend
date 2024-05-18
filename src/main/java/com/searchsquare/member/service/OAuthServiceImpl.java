package com.searchsquare.member.service;

import static java.util.Objects.isNull;

import com.searchsquare.infra.oauth.NaverApiClient;
import com.searchsquare.infra.oauth.response.NaverLoginRes;
import com.searchsquare.member.repository.MemberRepository;
import com.searchsquare.member.service.command.NaverLoginCommand;
import com.searchsquare.member.service.dto.ExistMemberSearch;
import com.searchsquare.member.service.dto.MemberDto;
import com.searchsquare.member.service.dto.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OAuthServiceImpl implements OAuthService {

    @Value("${member.default-profile-img}")
    private String DEFAULT_PROFILE_IMG;
    private final NaverApiClient naverApiClient;
    private final MemberRepository memberRepository;

    @Override
    public MemberDto naverLogin(NaverLoginCommand cmd) {
        NaverLoginRes naverLoginRes = naverApiClient.requestOauthInfo(cmd.getAccessToken());
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
        return member;
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

package com.searchsquare.infra.oauth;

import com.searchsquare.infra.oauth.response.NaverLoginRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class NaverApiClient {

    @Value("${oauth.naver.url.api}")
    private String API_URL;
    private final RestTemplate restTemplate;

    public NaverLoginRes requestOauthInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        /* 헤더 토큰을 전달하기 위해 get 대신 post 메서드 사용 */
        ResponseEntity<NaverLoginRes> exchange = restTemplate.exchange(API_URL, HttpMethod.GET,
            request, NaverLoginRes.class);
        return exchange.getBody();
    }
}

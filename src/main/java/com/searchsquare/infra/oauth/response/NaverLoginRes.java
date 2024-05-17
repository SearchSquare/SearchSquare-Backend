package com.searchsquare.infra.oauth.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.searchsquare.member.service.dto.Provider;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverLoginRes {

    private Response response;
    private Provider provider;

    @ToString
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {

        private String nickname;
        private String name;
        private String email;
        private String gender;
        private String age;
        private String birthyear;
        private String profile_img;
    }

    public String getNickname() {
        return response.getNickname();
    }

    public String getName() {
        return response.getName();
    }

    public String getEmail() {
        return response.getEmail();
    }

    public String getGender() {
        return response.getGender();
    }

    public String getAge() {
        return response.getAge();
    }

    public String getBirthYear() {
        return response.getBirthyear();
    }

    public String getProfileImg() {
        return response.getProfile_img();
    }

    public Provider getProvider() {
        return Provider.NAVER;
    }
}

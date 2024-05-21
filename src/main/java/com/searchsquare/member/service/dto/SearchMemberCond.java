package com.searchsquare.member.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchMemberCond {

    private String email;
    private Provider provider;
}

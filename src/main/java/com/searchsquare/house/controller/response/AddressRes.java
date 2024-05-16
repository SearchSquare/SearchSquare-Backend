package com.searchsquare.house.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AddressRes {

    private String sidoName;
    private String gugunName;
    private String dongName;
    private String dongCode;
}

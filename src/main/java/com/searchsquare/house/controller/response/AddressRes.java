package com.searchsquare.house.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AddressRes {

    private String sido;
    private String gugun;
    private String dong;
    private String dongCode;
}

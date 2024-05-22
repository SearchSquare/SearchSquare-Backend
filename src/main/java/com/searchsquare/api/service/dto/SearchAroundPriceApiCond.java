package com.searchsquare.api.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchAroundPriceApiCond {

    private double lat;
    private double lng;
    private String dongCode;
    private int radius;
}

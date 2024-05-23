package com.searchsquare.house.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseDto {

    private int aptId;
    private String name;
    private double lat;
    private double lng;
    private int builtYear;
    private String jibun;
    @JsonProperty(value = "isPopular")
    private boolean popular;
    /* 주소 정보 */
    private AddressDto address;
    /* 최대, 최소 가격 */
    private PriceDto price;
}

package com.searchsquare.house.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseDealDto {

    private int houseDealId;
    private String nickname;
    private String area;
    private String floor;
    private int price;
    private String dealDate;
}

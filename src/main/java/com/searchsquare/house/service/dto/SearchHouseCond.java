package com.searchsquare.house.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchHouseCond {

    private String dongCode;
    private Integer lastAptId;
    private Integer size;
}

package com.searchsquare.api.controller.facade;

import com.searchsquare.api.aop.ApiLogging;
import com.searchsquare.api.service.ApiService;
import com.searchsquare.api.service.KeyService;
import com.searchsquare.api.service.dto.AroundPriceApiDto;
import com.searchsquare.api.service.dto.SearchAroundPriceApiCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class GetAroundPriceApiFacade {

    private final ApiService apiService;
    private final KeyService keyService;

    @ApiLogging
    public AroundPriceApiDto getAroundPrice(
        final String serviceKey,
        final SearchAroundPriceApiCond cond) {
        keyService.isValid(serviceKey);
        return apiService.getAroundPrice(cond);
    }
}

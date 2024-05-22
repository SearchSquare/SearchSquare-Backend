package com.searchsquare.api.service;

import com.searchsquare.api.service.dto.AroundPriceApiDto;
import com.searchsquare.api.service.dto.SearchAroundPriceApiCond;

public interface ApiService {

    AroundPriceApiDto getAroundPrice(SearchAroundPriceApiCond cond);
}

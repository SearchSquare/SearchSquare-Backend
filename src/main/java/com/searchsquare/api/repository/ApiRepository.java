package com.searchsquare.api.repository;

import com.searchsquare.api.service.dto.HousePriceApiDto;
import com.searchsquare.api.service.dto.SearchAroundPriceApiCond;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiRepository {


    List<HousePriceApiDto> getTargetPriceList(SearchAroundPriceApiCond cond);

    List<HousePriceApiDto> getAroundPriceList(SearchAroundPriceApiCond cond);

    void save(int memberId, String serviceKey);
}

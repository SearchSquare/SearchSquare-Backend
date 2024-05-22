package com.searchsquare.api.service;

import com.searchsquare.api.repository.ApiRepository;
import com.searchsquare.api.service.dto.AroundPriceApiDto;
import com.searchsquare.api.service.dto.HousePriceApiDto;
import com.searchsquare.api.service.dto.SearchAroundPriceApiCond;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ApiServiceImpl implements ApiService {

    private final ApiRepository apiRepository;

    @Transactional(readOnly = true)
    @Override
    public AroundPriceApiDto getAroundPrice(SearchAroundPriceApiCond cond) {
        List<HousePriceApiDto> target = apiRepository.getTargetPriceList(cond);
        List<HousePriceApiDto> around = apiRepository.getAroundPriceList(cond);
        Set<String> yearSet = target.stream().map(HousePriceApiDto::getYear)
            .collect(Collectors.toSet());
        around.removeIf(dto -> !yearSet.contains(dto.getYear()));
        return AroundPriceApiDto.builder()
            .target(target)
            .around(around)
            .build();
    }
}

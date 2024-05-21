package com.searchsquare.house.service;

import com.searchsquare.house.repository.HouseRepository;
import com.searchsquare.house.service.dto.AddressDto;
import com.searchsquare.house.service.dto.AroundPriceDto;
import com.searchsquare.house.service.dto.HouseDealDto;
import com.searchsquare.house.service.dto.HouseDto;
import com.searchsquare.house.service.dto.HousePriceDto;
import com.searchsquare.house.service.dto.SearchAroundPriceCond;
import com.searchsquare.house.service.dto.SearchHouseCond;
import com.searchsquare.house.service.dto.SearchHouseDealCond;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    private final int MAX_RADIUS = 500;

    @Transactional(readOnly = true)
    @Override
    public List<AddressDto> getSido() {
        return houseRepository.getSido();
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDto> getGugun(String dongCode) {
        return houseRepository.getGugun(dongCode);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDto> getDong(String dongCode) {
        return houseRepository.getDong(dongCode);
    }

    @Transactional(readOnly = true)
    @Override
    public List<HouseDto> getHouseList(SearchHouseCond cond) {
        return houseRepository.getHouseList(cond);
    }

    @Transactional(readOnly = true)
    @Override
    public List<HouseDealDto> getDealList(SearchHouseDealCond cond) {
        return houseRepository.getDealList(cond);
    }

    @Override
    public AroundPriceDto getAroundPriceList(SearchAroundPriceCond cond) {
        List<HousePriceDto> target = houseRepository.getTargetPriceList(cond);
        List<HousePriceDto> around = houseRepository.getAroundPriceList(cond);
        return AroundPriceDto.builder()
            .target(target)
            .around(around)
            .build();
    }
}

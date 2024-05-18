package com.searchsquare.house.service;

import com.searchsquare.house.service.dto.AddressDto;
import com.searchsquare.house.service.dto.HouseDto;
import com.searchsquare.house.service.dto.SearchHouseCond;
import java.util.List;

public interface HouseService {

    List<AddressDto> getSido();

    List<AddressDto> getGugun(String dongCode);

    List<AddressDto> getDong(String dongCode);

    List<HouseDto> getHouseList(SearchHouseCond cond);
}

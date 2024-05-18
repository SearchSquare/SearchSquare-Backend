package com.searchsquare.house.repository;

import com.searchsquare.house.service.dto.AddressDto;
import com.searchsquare.house.service.dto.HouseDto;
import com.searchsquare.house.service.dto.SearchHouseCond;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseRepository {

    List<AddressDto> getSido();

    List<AddressDto> getGugun(String dongCode);

    List<AddressDto> getDong(String dongCode);

    List<HouseDto> getHouseList(SearchHouseCond cond);
}

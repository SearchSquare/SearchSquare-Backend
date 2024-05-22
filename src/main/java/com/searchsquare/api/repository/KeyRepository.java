package com.searchsquare.api.repository;

import com.searchsquare.api.service.dto.CreateTokenDto;
import com.searchsquare.api.service.dto.ServiceKeyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KeyRepository {

    void save(CreateTokenDto createTokenDto);

    ServiceKeyDto getServiceKey(int memberId);
}

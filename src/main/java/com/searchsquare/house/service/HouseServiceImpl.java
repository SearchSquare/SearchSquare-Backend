package com.searchsquare.house.service;

import com.searchsquare.house.controller.response.AddressRes;
import com.searchsquare.house.repository.HouseRepository;
import com.searchsquare.house.service.dto.AddressDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    private static List<AddressRes> toAddressRes(List<AddressDto> res) {
        return res.stream().map(addressDto -> AddressRes.builder()
                .sido(addressDto.getSido())
                .gugun(addressDto.getGugun())
                .dong(addressDto.getDong())
                .dongCode(addressDto.getDongCode())
                .build())
            .toList();
    }

    @Override
    public List<AddressRes> getSido() {
        List<AddressDto> res = houseRepository.getSido();
        return toAddressRes(res);
    }

    @Override
    public List<AddressRes> getGugun(String dongCode) {
        List<AddressDto> res = houseRepository.getGugun(dongCode);
        return toAddressRes(res);
    }

    @Override
    public List<AddressRes> getDong(String dongCode) {
        List<AddressDto> res = houseRepository.getDong(dongCode);
        return toAddressRes(res);
    }
}

package com.searchsquare.house.service;

import com.searchsquare.house.controller.response.AddressRes;
import java.util.List;

public interface HouseService {

    List<AddressRes> getSido();

    List<AddressRes> getGugun(String dongCode);

    List<AddressRes> getDong(String dongCode);
}

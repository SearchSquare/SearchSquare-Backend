package com.searchsquare.api.service;

import com.searchsquare.api.service.dto.ServiceKeyDto;

public interface KeyService {

    void isValid(String serviceKey);

    ServiceKeyDto createKey(String accessToken);
}

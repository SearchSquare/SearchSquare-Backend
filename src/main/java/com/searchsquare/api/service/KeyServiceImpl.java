package com.searchsquare.api.service;

import com.searchsquare.api.repository.KeyRepository;
import com.searchsquare.api.service.dto.CreateTokenDto;
import com.searchsquare.api.service.dto.ServiceKeyDto;
import com.searchsquare.core.util.JwtUtil;
import com.searchsquare.core.util.ServiceKeyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeyServiceImpl implements KeyService {

    private final int DAILY_LIMIt = 100;
    private final JwtUtil jwtUtil;
    private final ServiceKeyUtil serviceKeyUtil;
    private final KeyRepository keyRepository;

    @Override
    public void isValid(String serviceKey) {
        if (!serviceKeyUtil.checkServiceKey(serviceKey)) {
            throw new RuntimeException();
        }
    }

    @Override
    public ServiceKeyDto createKey(String accessToken) {
        int memberId = toInt(jwtUtil.getMemberId(accessToken));
        String serviceKey = serviceKeyUtil.createServiceKey(DAILY_LIMIt);
        keyRepository.save(CreateTokenDto.builder()
            .memberId(memberId)
            .serviceKey(serviceKey)
            .build());
        return keyRepository.getServiceKey(memberId);
    }

    private int toInt(String memberId) {
        return Integer.parseInt(memberId);
    }
}

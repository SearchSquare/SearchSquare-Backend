package com.searchsquare.api.service;

import com.searchsquare.api.repository.KeyRepository;
import com.searchsquare.api.service.dto.CreateTokenDto;
import com.searchsquare.api.service.dto.ServiceKeyDto;
import com.searchsquare.core.util.JwtUtil;
import com.searchsquare.core.util.ServiceKeyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class KeyServiceImpl implements KeyService {

    @Value("${service-key.daily-limit}")
    private int DAILY_LIMIt;
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
        ServiceKeyDto previousServiceKey = keyRepository.getServiceKey(memberId);
        if (previousServiceKey != null) {
            log.info("기존 Service Key 중지");
            keyRepository.remove(previousServiceKey);
        }
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

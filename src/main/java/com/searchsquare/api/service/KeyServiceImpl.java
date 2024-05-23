package com.searchsquare.api.service;

import static java.util.Objects.isNull;

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
    public ServiceKeyDto getServiceKey(String accessToken) {
        int memberId = toInt(jwtUtil.getMemberId(accessToken));
        ServiceKeyDto previousServiceKey = keyRepository.getServiceKey(memberId);
        /* 이미 사용 가능한 키가 있는 경우 */
        if (previousServiceKey != null && serviceKeyUtil.checkServiceKey(
            previousServiceKey.getServiceKey())) {
            log.info("이미 사용 가능한 키 존재");
            return previousServiceKey;
        }
        if (isNull(previousServiceKey)) {
            log.info("첫 서비스 키 발급");
            createAndSave(memberId);
        } else if (!serviceKeyUtil.checkServiceKey(previousServiceKey.getServiceKey())) {
            log.info("기존 서비스 키 만료 & 새롭게 갱신");
            keyRepository.remove(previousServiceKey);
            createAndSave(memberId);
        }
        return keyRepository.getServiceKey(memberId);
    }

    private void createAndSave(int memberId) {
        String serviceKey = serviceKeyUtil.createServiceKey(DAILY_LIMIt);
        keyRepository.save(CreateTokenDto.builder()
            .memberId(memberId)
            .serviceKey(serviceKey)
            .build());
    }

    private int toInt(String memberId) {
        return Integer.parseInt(memberId);
    }
}

package com.searchsquare.house.service;

import com.searchsquare.house.repository.HouseLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class HouseLogServiceImpl implements HouseLogService {

    private final HouseLogRepository houseLogRepository;

    @Override
    public void saveViewLog(int houseId) {
        houseLogRepository.saveViewLog(houseId);
    }
}

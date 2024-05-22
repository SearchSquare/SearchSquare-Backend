package com.searchsquare.admin.service;

import com.searchsquare.admin.repository.AdminRepository;
import com.searchsquare.admin.service.dto.MonthlyUserStatsDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public List<MonthlyUserStatsDto> getMonthlyUserStats() {
        return adminRepository.getMonthlyUserStats();
    }
}

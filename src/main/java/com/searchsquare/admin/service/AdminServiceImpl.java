package com.searchsquare.admin.service;

import com.searchsquare.admin.repository.AdminRepository;
import com.searchsquare.admin.service.dto.GenderStatsDto;
import com.searchsquare.admin.service.dto.MonthlyMemberStatsDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public List<MonthlyMemberStatsDto> getMonthlyMemberStats() {
        return adminRepository.getMonthlyMemberStats();
    }

    @Override
    public List<GenderStatsDto> getGenderStats() {
        return adminRepository.getGenderStats();
    }
}

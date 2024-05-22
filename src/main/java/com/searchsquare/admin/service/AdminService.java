package com.searchsquare.admin.service;

import com.searchsquare.admin.service.dto.GenderStatsDto;
import com.searchsquare.admin.service.dto.MonthlyMemberStatsDto;
import java.util.List;

public interface AdminService {

    List<MonthlyMemberStatsDto> getMonthlyMemberStats();

    List<GenderStatsDto> getGenderStats();
}

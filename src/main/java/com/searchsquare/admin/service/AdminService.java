package com.searchsquare.admin.service;

import com.searchsquare.admin.service.dto.MonthlyUserStatsDto;
import java.util.List;

public interface AdminService {

    List<MonthlyUserStatsDto> getMonthlyUserStats();
}

package com.searchsquare.admin.repository;

import com.searchsquare.admin.service.dto.MonthlyUserStatsDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRepository {

    List<MonthlyUserStatsDto> getMonthlyUserStats();
}

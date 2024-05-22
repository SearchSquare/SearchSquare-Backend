package com.searchsquare.admin.repository;

import com.searchsquare.admin.service.dto.AgeStatsDto;
import com.searchsquare.admin.service.dto.GenderStatsDto;
import com.searchsquare.admin.service.dto.MonthlyMemberStatsDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRepository {

    List<MonthlyMemberStatsDto> getMonthlyMemberStats();

    List<GenderStatsDto> getGenderStats();

    List<AgeStatsDto> getAgeStats();
}

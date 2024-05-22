package com.searchsquare.admin.controller;

import com.searchsquare.admin.service.AdminService;
import com.searchsquare.admin.service.dto.AgeStatsDto;
import com.searchsquare.admin.service.dto.GenderStatsDto;
import com.searchsquare.admin.service.dto.MonthlyMemberStatsDto;
import com.searchsquare.core.response.BaseResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final GenericResponseService responseBuilder;

    @GetMapping("/rate/members")
    public ResponseEntity<BaseResponse<List<MonthlyMemberStatsDto>>> getMonthlyMemberStats() {
        List<MonthlyMemberStatsDto> res = adminService.getMonthlyMemberStats();
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @GetMapping("/rate/gender")
    public ResponseEntity<BaseResponse<List<GenderStatsDto>>> getGenderStats() {
        List<GenderStatsDto> res = adminService.getGenderStats();
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @GetMapping("/rate/age")
    public ResponseEntity<BaseResponse<List<AgeStatsDto>>> getAgeStats() {
        List<AgeStatsDto> res = adminService.getAgeStats();
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}

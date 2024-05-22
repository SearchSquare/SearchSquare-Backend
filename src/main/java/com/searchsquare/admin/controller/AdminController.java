package com.searchsquare.admin.controller;

import com.searchsquare.admin.service.AdminService;
import com.searchsquare.admin.service.dto.MonthlyUserStatsDto;
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

    @GetMapping("/members/rate")
    public ResponseEntity<BaseResponse<List<MonthlyUserStatsDto>>> getMonthlyUserStats() {
        List<MonthlyUserStatsDto> res = adminService.getMonthlyUserStats();
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}

package com.searchsquare.api.controller;

import com.searchsquare.api.controller.facade.GetAroundPriceApiFacade;
import com.searchsquare.api.service.AroundPriceApiDto;
import com.searchsquare.api.service.KeyService;
import com.searchsquare.api.service.dto.SearchAroundPriceApiCond;
import com.searchsquare.api.service.dto.ServiceKeyDto;
import com.searchsquare.core.response.BaseResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    private final GetAroundPriceApiFacade getAroundPriceApiFacade;
    private final KeyService keyService;

    @GetMapping("/house/around/price")
    public ResponseEntity<BaseResponse<AroundPriceApiDto>> getAroundPrice(
        @RequestParam("service-key") String serviceKey,
        @RequestParam("lat") double lat,
        @RequestParam("lng") double lng,
        @RequestParam("dong-code") String dongCode,
        @RequestParam("radius") @Max(value = 500, message = "검색 가능한 범위를 벗어났습니다.") @Min(value = 0, message = "검색 가능한 범위를 벗어났습니다.") int radius
    ) {
        AroundPriceApiDto res = getAroundPriceApiFacade.getAroundPrice(serviceKey,
            SearchAroundPriceApiCond.builder()
                .lat(lat)
                .lng(lng)
                .dongCode(dongCode)
                .radius(radius)
                .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @GetMapping("/key")
    public ResponseEntity<BaseResponse<ServiceKeyDto>> getServiceKey(
        @RequestHeader("Authorization") String accessToken) {
        ServiceKeyDto res = keyService.createKey(accessToken);
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}

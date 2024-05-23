package com.searchsquare.house.controller;

import com.searchsquare.core.response.BaseResponse;
import com.searchsquare.house.service.HouseLogService;
import com.searchsquare.house.service.HouseService;
import com.searchsquare.house.service.dto.AddressDto;
import com.searchsquare.house.service.dto.AroundPriceDto;
import com.searchsquare.house.service.dto.HouseDealDto;
import com.searchsquare.house.service.dto.HouseDto;
import com.searchsquare.house.service.dto.SearchAroundPriceCond;
import com.searchsquare.house.service.dto.SearchHouseCond;
import com.searchsquare.house.service.dto.SearchHouseDealCond;
import jakarta.validation.constraints.Max;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseController {

    private static final Logger log = LoggerFactory.getLogger(HouseController.class);
    private final HouseService houseService;
    private final HouseLogService houseLogService;

    /**
     * 아파트 목록을 조회한다. (no-offset 페이징 처리)
     *
     * @param dongCode    검색하고자 하는 동 코드
     * @param size        한 페이지에 조회할 개수
     * @param lastHouseId 두번째 조회부터 유효
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<HouseDto>>> getHouseList(
        @RequestParam("dong-code") String dongCode,
        @RequestParam("size") Integer size,
        @RequestParam(value = "last-house-id", required = false) Integer lastHouseId
    ) {
        List<HouseDto> res = houseService.getHouseList(SearchHouseCond.builder()
            .dongCode(dongCode)
            .lastHouseId(lastHouseId)
            .size(size)
            .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    /**
     * 시/도 목록을 조회한다.
     *
     * @return
     */
    @GetMapping("/address/sido")
    public ResponseEntity<BaseResponse<List<AddressDto>>> getSido() {
        List<AddressDto> res = houseService.getSido();
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    /**
     * 시/구/군 목록을 조회한다.
     *
     * @param dongCode 2자리 시/도 코드
     * @return
     */
    @GetMapping("/address/gugun")
    public ResponseEntity<BaseResponse<List<AddressDto>>> getGugun(
        @RequestParam("dong-code") String dongCode) {
        List<AddressDto> res = houseService.getGugun(dongCode);
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    /**
     * 동 목록을 조회한다.
     *
     * @param dongCode 5자리 시/구/군 코드
     * @return
     */
    @GetMapping("/address/dong")
    public ResponseEntity<BaseResponse<List<AddressDto>>> getDong(
        @RequestParam("dong-code") String dongCode) {
        List<AddressDto> res = houseService.getDong(dongCode);
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @GetMapping("/deal/{house-id}")
    public ResponseEntity<BaseResponse<List<HouseDealDto>>> getDealList(
        @PathVariable("house-id") int houseId,
        @RequestParam("size") Integer size,
        @RequestParam(value = "last-deal-id", required = false) Integer lastDealId
    ) {
        List<HouseDealDto> res = houseService.getDealList(SearchHouseDealCond.builder()
            .size(size)
            .houseId(houseId)
            .lastDealId(lastDealId)
            .build());
        log.info("매물 조회");
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @GetMapping("/price/{house-id}")
    public ResponseEntity<BaseResponse<AroundPriceDto>> getAroundPriceList(
        @PathVariable("house-id") int houseId,
        @RequestParam("lat") double lat,
        @RequestParam("lng") double lng,
        @RequestParam("dong-code") String dongCode,
        @RequestParam("radius") @Max(value = 500, message = "검색 가능한 범위를 벗어났습니다.") int radius
    ) {
        AroundPriceDto res = houseService.getAroundPriceList(SearchAroundPriceCond.builder()
            .lat(lat)
            .lng(lng)
            .dongCode(dongCode)
            .radius(radius)
            .build());
        houseLogService.saveViewLog(houseId);
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}

package com.searchsquare.house.controller;

import com.searchsquare.core.response.BaseResponse;
import com.searchsquare.house.service.HouseService;
import com.searchsquare.house.service.dto.AddressDto;
import com.searchsquare.house.service.dto.HouseDto;
import com.searchsquare.house.service.dto.SearchHouseCond;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;

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

    /**
     * 아파트 목록을 조회한다. (no-offset 페이징 처리)
     *
     * @param dongCode  검색하고자 하는 동 코드
     * @param size      한 페이지에 조회할 개수
     * @param lastAptId 두번째 조회부터 유효
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<HouseDto>>> getHouseList(
        @RequestParam("dong-code") String dongCode,
        @RequestParam("size") Integer size,
        @RequestParam(value = "last-apt-id", required = false) Integer lastAptId
    ) {
        List<HouseDto> res = houseService.getHouseList(SearchHouseCond.builder()
            .dongCode(dongCode)
            .lastAptId(lastAptId)
            .size(size)
            .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}

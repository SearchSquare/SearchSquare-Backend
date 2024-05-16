package com.searchsquare.house.controller;

import com.searchsquare.core.response.BaseResponse;
import com.searchsquare.house.controller.response.AddressRes;
import com.searchsquare.house.service.HouseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/address/sido")
    public ResponseEntity<BaseResponse<List<AddressRes>>> getSido() {
        List<AddressRes> res = houseService.getSido();
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}

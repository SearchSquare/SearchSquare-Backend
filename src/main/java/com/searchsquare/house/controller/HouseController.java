package com.searchsquare.house.controller;

import com.searchsquare.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;

}

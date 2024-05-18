package com.searchsquare.house.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.searchsquare.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class HouseControllerTest extends MockMvcTest {

    @Autowired
    protected MockMvc mockMvc;

    @DisplayName("시/도 목록을 조회한다.")
    @Test
    void getSidoSuccess() throws Exception {
        //given
        String url = "/house/address/sido";

        // then
        mockMvc.perform(
                get(url)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("구군 목록을 조회한다.")
    @Test
    void getGugunSuccess() throws Exception {
        //given
        String url = "/house/address/gugun";

        // then
        mockMvc.perform(
                get(url)
                    .param("dong-code", "11")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("동 목록을 조회한다.")
    @Test
    void getDongSuccess() throws Exception {
        //given
        String url = "/house/address/dong";

        // then
        mockMvc.perform(
                get(url)
                    .param("dong-code", "11110")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("동 코드로 아파트 목록을 최초로 조회한다.")
    @Test
    void firstGetHouseListSuccess() throws Exception {
        //given
        String url = "/house/";

        // then
        mockMvc.perform(
                get(url)
                    .param("dong-code", "1120011200")
                    .param("size", "10")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("동 코드와 직전 아파트 id로 아파트 목록 2페이지를 조회한다.")
    @Test
    void secondGetHouseListSuccess() throws Exception {
        //given
        String url = "/house/";

        // then
        mockMvc.perform(
                get(url)
                    .param("dong-code", "1120011200")
                    .param("size", "10")
                    .param("last-house-id", "284066")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isOk());
    }

}
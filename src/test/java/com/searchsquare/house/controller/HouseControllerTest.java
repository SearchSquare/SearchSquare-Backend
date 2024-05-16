package com.searchsquare.house.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class HouseControllerTest {

    @Autowired
    private MockMvc mockMvc; // mockMvc 생성

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

}
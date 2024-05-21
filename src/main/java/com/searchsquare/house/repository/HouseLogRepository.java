package com.searchsquare.house.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseLogRepository {

    void saveViewLog(int houseId);
}

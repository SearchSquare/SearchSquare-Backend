package com.searchsquare.api.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiLogRepository {

    void save(String serviceKey);
}

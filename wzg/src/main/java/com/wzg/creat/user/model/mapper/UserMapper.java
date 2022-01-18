package com.wzg.creat.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from (${sql})tt")
    List<Map<String, Object>> getResultByQuota(@Param("sql")String sql);

}
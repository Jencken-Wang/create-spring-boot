package com.wzg.creat.user.model.mapper;

import com.wzg.creat.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from (${sql})tt")
    List<Map<String, Object>> getResultByQuota(@Param("sql")String sql);

}
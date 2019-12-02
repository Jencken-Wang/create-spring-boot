package com.wzg.creat.user.model.mapper;

import com.wzg.creat.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
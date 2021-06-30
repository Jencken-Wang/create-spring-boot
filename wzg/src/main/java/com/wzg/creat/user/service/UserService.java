package com.wzg.creat.user.service;

import com.wzg.creat.user.model.entity.User;
import com.wzg.creat.user.service.Bean.UserBean;

import java.util.List;
import java.util.Map;

public interface UserService {


    List<User> queryUser(UserBean userBean);


    void test(String s);


    List<Map<String, Object>> getResultByQuota(String sql);
}

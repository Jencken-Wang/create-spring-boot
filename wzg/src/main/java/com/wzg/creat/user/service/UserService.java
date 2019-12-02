package com.wzg.creat.user.service;

import com.wzg.creat.user.model.entity.User;
import com.wzg.creat.user.service.Bean.UserBean;

import java.util.List;

public interface UserService {


    List<User> queryUser(UserBean userBean);
}

package com.wzg.creat.user.service.Impl;

import com.wzg.creat.user.model.entity.User;
import com.wzg.creat.user.model.mapper.UserMapper;
import com.wzg.creat.user.service.Bean.UserBean;
import com.wzg.creat.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    public List<User> queryUser(UserBean userBean) {
//        User user =
//        Page page = PageHelper.startPage(userBean.getPage().getPageNum(),
//                userBean.getPage().getPageSize(), "");
        List<User> list = userMapper.selectAll();
        return list;
    }

    @Override
    public void test(String s) {
        System.out.println(s);
    }

    @Override
    public List<Map<String, Object>> getResultByQuota(String sql) {
        return userMapper.getResultByQuota(sql);
    }
}

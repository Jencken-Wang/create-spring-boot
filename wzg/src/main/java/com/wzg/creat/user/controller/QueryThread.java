package com.wzg.creat.user.controller;

import cn.hutool.json.JSONUtil;
import com.wzg.creat.user.model.entity.User;
import com.wzg.creat.user.service.UserService;
import com.wzg.util.SpringApplicationContextHolder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class QueryThread implements Runnable{

    private SpringApplicationContextHolder contextHolder;

    private UserService userService;

    public QueryThread(SpringApplicationContextHolder springApplicationContextHolder){
        this.contextHolder = springApplicationContextHolder;
        this.userService = contextHolder.getBean(UserService.class);
    }

    @Override
    public void run() {
        System.out.println("开始线程内部查询");
        List<Map<String, Object>> list = userService.getResultByQuota("select * from t_user");
        System.out.println(JSONUtil.toJsonStr(list));
        System.out.println("结束线程内部查询");
    }
}

package com.wzg.creat.user.controller;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.wzg.creat.common.ret.RetInfo;
import com.wzg.creat.user.model.entity.User;
import com.wzg.creat.user.service.Bean.UserBean;
import com.wzg.creat.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RequestMapping("/user")
@RestController
@Slf4j
@Api("获取用户")
public class UserController{

    @Resource
    private UserService userService;

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public RetInfo queryUser(@ModelAttribute UserBean userBean) {
        RetInfo retInfo = new RetInfo();
        try {
            List<User> list = userService.queryUser(userBean);
            retInfo.setRetData(list);
            retInfo.setRetMsg("获取成功");
            retInfo.setRetCode(RetInfo.SUCCESS);
        } catch (Exception e) {
            log.error(ExceptionUtil.stacktraceToString(e));
            retInfo.setRetMsg("失败");
            retInfo.setRetCode(RetInfo.FAIL);
        }
        return retInfo;
    }

    



    @RequestMapping(value = "/gettest", method = RequestMethod.GET)
    public RetInfo test(@ModelAttribute UserBean userBean) {
        RetInfo retInfo = new RetInfo();

        List<User> list = userService.queryUser(userBean);
        retInfo.setRetData(list);
        retInfo.setRetMsg("获取成功");
        retInfo.setRetCode(RetInfo.SUCCESS);
        int a = 1 / 0;
        return retInfo;
    }
}

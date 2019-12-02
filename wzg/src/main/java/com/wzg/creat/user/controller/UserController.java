package com.wzg.creat.user.controller;


import com.wzg.creat.common.ret.RetInfo;
import com.wzg.creat.user.model.entity.User;
import com.wzg.creat.user.service.Bean.UserBean;
import com.wzg.creat.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/user")
@RestController
@Slf4j
@Api("获取用户")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public RetInfo queryUser(@ModelAttribute UserBean userBean){
        RetInfo retInfo = new RetInfo();
        try {
            List<User> list = userService.queryUser(userBean);
            retInfo.setRetData(list);
            retInfo.setRetMsg("获取成功");
            retInfo.setRetCode(RetInfo.SUCCESS);
        } catch (Exception e){
            retInfo.setRetMsg("失败");
            retInfo.setRetCode(RetInfo.FAIL);
        }
        return retInfo;
    }


}

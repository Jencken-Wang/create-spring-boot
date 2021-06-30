package com.wzg.creat.user.controller;


import com.wzg.creat.base.BaseController;
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
            retInfo.setRetMsg("失败");
            retInfo.setRetCode(RetInfo.FAIL);
        }
        return retInfo;
    }

    
    /** 
     * @description:  
     * @param:  
     * @return:  
     * @author wangzg
     * @date: 2021/3/5 17:57
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFile(@RequestParam String filePath, @RequestParam String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            FileInputStream inputStream = null;
            BufferedInputStream bufferIn = null;
            ServletOutputStream os = null;
            byte[] bytes = new byte[1024];
            int a;
            try {
                inputStream = new FileInputStream(file);
                bufferIn = new BufferedInputStream(inputStream);
                os = response.getOutputStream();
                while ((a = bufferIn.read(bytes)) != -1) {
                    os.write(bytes, 0, a);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                os.close();
                bufferIn.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

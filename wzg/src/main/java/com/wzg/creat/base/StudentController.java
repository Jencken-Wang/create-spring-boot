package com.wzg.creat.base;

import com.wzg.creat.user.controller.QueryThread;
import com.wzg.creat.user.service.UserService;
import com.wzg.util.ExecutionResult;
import com.wzg.util.SpringApplicationContextHolder;
import lombok.Data;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StudentController extends BaseController {

    @Resource
    SpringApplicationContextHolder contextHolder;

    public void changeCity(String a){
        setCity(a);
        System.out.println("方法执行结束");
    }

    public void changeScholl(String b){
        setSchool(b);
    }

    @RequestMapping("/testAspect")
    @ExecutionResult(message = "123", value = "abcd")
    public void start(){
        this.changeCity("cq");
    }


//    public static void main(String[] args) {
////        StudentController studentController = new StudentController();
////        studentController.start();
//    }

    @RequestMapping("/testThreadSql")
    public void test(){
        QueryThread queryThread = new QueryThread(contextHolder);
        new Thread(queryThread).start();
    }

}

package com.wzg.creat.base;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wzg.creat.common.injectTest.InjectClassDemo;
import com.wzg.creat.user.controller.QueryThread;
import com.wzg.util.ExecutionResult;
import com.wzg.util.SpringApplicationContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentController extends BaseController {

    @Resource
    SpringApplicationContextHolder contextHolder;

    @Resource
    InjectClassDemo injectClassDemo;

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



    @RequestMapping("/testparam")
    public String testparam(Model model){
        injectClassDemo.getAges().add(12);
        System.out.println(model.getAttribute("test"));
        System.out.println(injectClassDemo.getAges());
        return "";
    }

}

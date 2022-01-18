package com.wzg.creat;

import com.wzg.creat.common.injectTest.InjectClassDemo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.wzg")
@MapperScan("com.wzg.creat.*.model.mapper")
public class newController {
    @RequestMapping("/hello")
    @ResponseBody
    String home(){
        return "Hello world";
    }
    public static void main(String[] args){
        ConfigurableApplicationContext run = SpringApplication.run(newController.class, args);
        run.getBean("injectClassDemo", InjectClassDemo.class);
        System.out.println(1111);
        System.out.println("                                                                                                                                                                                               \n" +
                "                   _ooOoo_\n" +
                "                  o8888888o\n" +
                "                  88\" . \"88\n" +
                "                  (| -_- |)\n" +
                "                  O\\  =  /O\n" +
                "               ____/`---'\\____\n" +
                "             .'  \\\\|     |//  `.\n" +
                "            /  \\\\|||  :  |||//  \\\n" +
                "           /  _||||| -:- |||||-  \\\n" +
                "           |   | \\\\\\  -  /// |   |\n" +
                "           | \\_|  ''\\---/''  |   |\n" +
                "           \\  .-\\__  `-`  ___/-. /\n" +
                "         ___`. .'  /--.--\\  `. . __\n" +
                "      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
                "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
                "======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "                   `=---='\n" +
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                "         佛祖保佑       永无BUG\n" +
                " 佛曰:\n" +
                "         写字楼里写字间，写字间里程序员；\n" +
                "         程序人员写程序，又拿程序换酒钱。\n" +
                "         酒醒只在网上坐，酒醉还来网下眠；\n" +
                "         酒醉酒醒日复日，网上网下年复年。\n" +
                "         但愿老死电脑间，不愿鞠躬老板前；\n" +
                "         奔驰宝马贵者趣，公交自行程序员。\n" +
                "         别人笑我忒疯癫，我笑自己命太贱；\n" +
                "         不见满街漂亮妹，哪个归得程序员？");

    }
}

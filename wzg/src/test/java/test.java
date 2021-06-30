import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wzg.creat.base.StudentController;
import com.wzg.creat.newController;
import com.wzg.creat.user.model.mapper.UserMapper;
import com.wzg.creat.user.service.Bean.UserBean;
import com.wzg.creat.user.service.UserService;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = newController.class)
public class test {

    @Resource
    UserService userService;

    @Test
    public void test(){

        Map map = new HashMap();
        map.put("电子渠道业务支撑系统（网厅-商城-掌厅-短厅-B2B)", "电子渠道业务支撑系统（网厅-商城-掌厅-短厅-B2B) true");
        String str = "电子渠道业务支撑系统（网厅/商城/掌厅/短厅/B2B)";
        System.out.println(str.replace('/','-'));
        System.out.println(map.get(str.replace('/','-')));

        String str1 = "CRM和和BOSS支撑系统";
        System.out.println(str1.replace('和', '&'));
    }

    @Test
    public void  test2(){
        String str = "a,b,c";
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();

        String[] astr = str.split(",");
        a = Arrays.asList(astr);
        a = new ArrayList<>(a);
        System.out.println(a.size());
//        a.add("a");
//        a.add("b");
//        a.add("c");
        b.add("a");
        b.add("b");
        b.add("d");
        a.removeAll(b);
        System.out.println(a);
    }

    @Test
    public void  test3(){
        String regString = "(script|svg|alert|prompt|onload|onmouseover|onfocus|onerror|onkeyup|onkeydown|xss|Eval(?=\\(.*\\))|Console\\.log(?=\\(.*\\))|\\+|\\/|\\`)";
        Pattern pattern = Pattern.compile(regString, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher("+123123/sfdkslajf`543543");
        String value = matcher.replaceAll("");
        System.out.println(value);
    }

    @Test
    public void  test4(){
        UserBean bean1 = new UserBean();
        bean1.setDescription("asd");
        bean1.setNickname("a");
        UserBean bean2 = new UserBean();
        bean2.setDescription("asd");
        bean2.setNickname("b");
        List<UserBean> user = new ArrayList<>();
        user.add(bean1);
        user.add(bean1);
        user.add(bean1);
        user.add(bean2);
        user.add(bean2);
        Map<String, Long> map = user.stream().collect(Collectors.groupingBy(UserBean::getNickname, Collectors.counting()));
        System.out.println(map.toString());
    }


    @Test
    public void test5(){
        JSONObject object = new JSONObject("");
//        object.put("a", 123);
        System.out.println(object.get("a"));
        JSONUtil.parseArray("[]");
    }


    @Test
    public void test6(){
        int a = 8;
    }


}

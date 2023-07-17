import com.wzg.creat.user.model.entity.User;
import com.wzg.util.DoubleCheckSingleton;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Literal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yaml.snakeyaml.reader.StreamReader;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class test {

    @Test
    public void test1() {
        System.out.println(tryCatch());
//        System.out.println(ret());
    }


    int tryCatch() {
        int num = 0;
        try {
            num = 5 / num;
        } catch (Exception e) {
            System.out.println("catch:" + num);
            return num = num + 1 ;
        } finally {
            System.out.println("finally:" + num);
//            return ++num;
        }
        return 10;
    }

    int ret() {
        int num = 0;
        return ++num;
    }

    @Test
    public void test2() throws Exception {

//        SingletonObject object1 = SingletonObject.getInstance();
//        SingletonObject object2 = SingletonObject.getInstance();
//        System.out.println(object1 == object2);


        Constructor constructor = DoubleCheckSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DoubleCheckSingleton instance1 = (DoubleCheckSingleton) constructor.newInstance();
        DoubleCheckSingleton instance2 = (DoubleCheckSingleton) constructor.newInstance();
        System.out.println(instance1 == instance2);


    }

    @Test
    public void test3() throws InterruptedException {
        TestObjThread testObjThread = new TestObjThread();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testObjThread.setA(2);
                }
            }).start();
        }

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testObjThread.setA(1);
                }
            }).start();
        }

        Thread.sleep(1000);

    }


    class TestObjThread {
       int a = 0;
        void setA(int b)  {
            try {

                if (b == 1) {
                    a = 1;
                    log.info(Thread.currentThread().getName() + " a 等于：" + a);
                }

                if (b == 2) {
                    log.info(Thread.currentThread().getName() + " bbbbbbbbbbbb========= 等于：" + a);
//                    Thread.sleep(10);
                    a = 2;
                    Thread.sleep(10);
                    log.info(Thread.currentThread().getName() + " bbbbbbbbbbbb 等于：" + a);
                }

//            a = b;
//            if (b == 1) {
//                log.info(Thread.currentThread().getName() + " " + a);
//            }
                log.info(Thread.currentThread().getName() + " " + a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test4() {

        StringBuilder builder = new StringBuilder();
        builder.append("123546789012354");
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.info(builder.toString());
                Thread.sleep(2000);
                builder.append("a");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                builder.append("b");
            }
        });

        t1.start();
        t2.start();

        log.info(builder.toString());
    }


    @Test
    public void test5() throws InterruptedException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++)
                        builder.append("a");
                }
            }).start();
        }

        Thread.sleep(1000);
        log.info(String.valueOf(builder.length()));
    }



    @Test
    public void test6(){

        List<Type> list = new ArrayList<Type>();
        Type user1 = new Type();
        Type user2 = new Type();
        Type user3 = new Type();
        Type user4 = new Type();
        user1.setId(1);
        user2.setId(2);
        user3.setId(3);
        user4.setId(4);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list = list.stream().filter(el -> el.getId() > 2).collect(Collectors.toList());
        System.out.println(list);


        System.out.println(Arrays.asList("a").contains("a"));

    }

    @Data
    class Type{
        Integer id;

        Double num;
    }

    class TypeParent extends Type {
    }


    @Test
    public void test7() {
        double a = 254.23478;
        DecimalFormat format = new DecimalFormat("#.00");
        format.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(format.format(2.345346));

        Type type = new Type();
        type.setNum((double)Math.round(a / 100 * 100)/100);
        System.out.println(type.num);


        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);


        Type type1 = new Type();
//        TypeParent parent = (TypeParent) type1;

    }


    @Test
    public void test8() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        try {

        } catch (Exception e) {
            ExceptionSOut(e);
        }
    }

    public <T> void sort(T obj){
        if (obj instanceof Integer) {
            System.out.println(obj.toString());
        } else {
            System.out.println(obj);
        }

    }


    public void ExceptionSOut(Exception e) throws IOException {
        try(StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);){
            e.printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        } catch(NullPointerException e1) {
            System.out.println("no wirter to close");
        }
    }

}

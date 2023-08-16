import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.wzg.creat.addData.model.entity.PmUnifiedResourcesSummeryInfoEveryTime;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

public class test2 {

    @Test
    public void test() {
        Calendar calendar = Calendar.getInstance();
        Date date1, date2;

        date1 = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        date2 = calendar.getTime();

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date1 == date2);


        PmUnifiedResourcesSummeryInfoEveryTime everyNow = new PmUnifiedResourcesSummeryInfoEveryTime();
        everyNow.setResourceType("aaa");
        PmUnifiedResourcesSummeryInfoEveryTime a1 = JSONUtil.toBean(JSONUtil.toJsonStr(everyNow), PmUnifiedResourcesSummeryInfoEveryTime.class);
        PmUnifiedResourcesSummeryInfoEveryTime a2 = JSONUtil.toBean(JSONUtil.toJsonStr(everyNow), PmUnifiedResourcesSummeryInfoEveryTime.class);

        System.out.println(a1 == a2);
    }


    @Test
    public void test2() {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);

        String date = DateUtil.formatDateTime(new Date());
        System.out.println(date);

        LocalDateTime localDateTime = DateUtil.parseLocalDateTime(date);
        System.out.println(localDateTime);

        System.out.println(ZoneId.systemDefault());
        System.out.println(LocalDateTime.now());
        LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.UTC);


        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MINUTE));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) /5 *5);
        System.out.println(calendar.get(Calendar.MINUTE));

    }

    @Test
    public void test3() {
        int row = 9;
        int maxNum = (row + 1) / 2;
        for (int i = 0; i < maxNum; i++) {
            for (int j = maxNum - 1 - i ; j > 0; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k < (i+1) * 2; k++) {
                System.out.print("*");
            }
            System.out.println("");
        }

        for (int i = 0; i < row - maxNum; i++) {
            for (int k = 0; k < i + 1; k++) {
                System.out.print(" ");
            }
            for (int j = 2 * (maxNum - 1 - i) - 1; j > 0 ; j--) {
                System.out.print("*");
            }

            System.out.println("");
        }
    }


    @Test
    public void test4() {
        Integer[] a = new Integer[5];
        for (int i = 0; i < 5; i++) {
            a[i] = i;
        }
        a[3] = null;
        for (int i = 0; i < a.length; i++) {
            System.out.println(i + "   " + a[i]);
        }
    }


    @Test
    public void testLogic() {

        System.out.println(true || false && true);
        System.out.println(true || false && false);
        System.out.println(true && false || true);
        System.out.println(true && false || false);

        System.out.println(false || true && false  || true && false);
    }

    @Test
    public void testAccess() {
        for (int i = 0; i < 100; i++) {
            System.out.println("第" + i+ "次访问");
            HttpUtil.get("http://localhost:1024/wzg/rpublish");
        }

    }

}

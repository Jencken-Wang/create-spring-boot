import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.impl.BeanConverter;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.wzg.creat.user.model.entity.PmUnifiedResourcesSummeryInfoEveryTime;
import org.junit.Test;

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
    }


}

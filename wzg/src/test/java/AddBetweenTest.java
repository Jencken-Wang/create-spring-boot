import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.wzg.creat.newController;
import com.wzg.creat.user.model.entity.PmUnifiedResourcesSummeryInfoEveryTime;
import com.wzg.creat.user.model.mapper.PmUnifiedResourcesSummeryInfoEveryTimeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

@SpringBootTest(classes = newController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AddBetweenTest {

    @Resource
    PmUnifiedResourcesSummeryInfoEveryTimeMapper everyTimeMapper;


    @Test
    public void add() {

        PmUnifiedResourcesSummeryInfoEveryTime everyTime = new PmUnifiedResourcesSummeryInfoEveryTime();
        everyTime.setResourceType("assignedServer");
        List<PmUnifiedResourcesSummeryInfoEveryTime> list = everyTimeMapper.select(everyTime);

        list.sort(Comparator.comparing(PmUnifiedResourcesSummeryInfoEveryTime::getInsertTime)) ;

        List<PmUnifiedResourcesSummeryInfoEveryTime> listAdd = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                PmUnifiedResourcesSummeryInfoEveryTime everyNow = list.get(i);
                PmUnifiedResourcesSummeryInfoEveryTime everyNext = list.get(i + 1);
//                System.out.println(DateUtil.formatDateTime(everyNow.getInsertTime()));
                Calendar ca = Calendar.getInstance();
                ca.setTime(everyNow.getInsertTime());
                ca.set(Calendar.SECOND, 0);
                ca.set(Calendar.MILLISECOND, 0);
                long startTime = ca.getTimeInMillis();
                ca.setTime(everyNext.getInsertTime());
                ca.set(Calendar.SECOND, 0);
                ca.set(Calendar.MILLISECOND, 0);
                long endTime = ca.getTimeInMillis();
                long indexTime = startTime;

                Calendar calendar = Calendar.getInstance();
                while (endTime > indexTime + 300000) {
                    calendar.setTimeInMillis(indexTime);
                    calendar.add(Calendar.MINUTE, 5);
                    PmUnifiedResourcesSummeryInfoEveryTime add = JSONUtil.toBean(JSONUtil.toJsonStr(everyNow), PmUnifiedResourcesSummeryInfoEveryTime.class);
                    add.setInsertTime(calendar.getTime());
                    add.setId(null);
                    listAdd.add(add);
                    indexTime = calendar.getTimeInMillis();
                }

            }
        }
        for (PmUnifiedResourcesSummeryInfoEveryTime addTime : listAdd) {
            System.out.println(DateUtil.formatDateTime(addTime.getInsertTime()));
//            everyTimeMapper.insert(addTime);
        }
    }


}

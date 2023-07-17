import com.wzg.creat.addData.service.IPmUnifiedResourcesSummeryInfoEveryTimeService;
import com.wzg.creat.newController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = newController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AddBetweenTest {

    @Resource
    IPmUnifiedResourcesSummeryInfoEveryTimeService summeryInfoEveryTimeService;

//    @Resource
//    PmUnifiedResourcesSummeryInfoEveryTimeMapper everyTimeMapper;

//    @Test
//    public void add() {
//
//        PmUnifiedResourcesSummeryInfoEveryTime everyTime = new PmUnifiedResourcesSummeryInfoEveryTime();
//        everyTime.setResourceType("onlineServer");
//        List<PmUnifiedResourcesSummeryInfoEveryTime> list = everyTimeMapper.selectList(new QueryWrapper<PmUnifiedResourcesSummeryInfoEveryTime>()
//                .eq("resourceType", "onlineServer"));
//
//        list.sort(Comparator.comparing(PmUnifiedResourcesSummeryInfoEveryTime::getInsertTime)) ;
//
//        List<PmUnifiedResourcesSummeryInfoEveryTime> listAdd = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            if (i < list.size() - 1) {
//                PmUnifiedResourcesSummeryInfoEveryTime everyNow = list.get(i);
//                PmUnifiedResourcesSummeryInfoEveryTime everyNext = list.get(i + 1);
////                System.out.println(DateUtil.formatDateTime(everyNow.getInsertTime()));
//                Calendar ca = Calendar.getInstance();
//                ca.setTime(LocalDateTimeUtil.everyNow.getInsertTime());
//                ca.set(Calendar.SECOND, 0);
//                ca.set(Calendar.MILLISECOND, 0);
//                long startTime = ca.getTimeInMillis();
//                ca.setTime(everyNext.getInsertTime());
//                ca.set(Calendar.SECOND, 0);
//                ca.set(Calendar.MILLISECOND, 0);
//                long endTime = ca.getTimeInMillis();
//                long indexTime = startTime;
//
//                Calendar calendar = Calendar.getInstance();
//                while (endTime > indexTime + 300000) {
//                    calendar.setTimeInMillis(indexTime);
//                    calendar.add(Calendar.MINUTE, 5);
//                    PmUnifiedResourcesSummeryInfoEveryTime add = JSONUtil.toBean(JSONUtil.toJsonStr(everyNow), PmUnifiedResourcesSummeryInfoEveryTime.class);
//                    add.setInsertTime(calendar.getTime());
//                    add.setId(null);
//                    listAdd.add(add);
//                    indexTime = calendar.getTimeInMillis();
//                }
//
//            }
//        }
//        for (PmUnifiedResourcesSummeryInfoEveryTime addTime : listAdd) {
////            System.out.println(DateUtil.formatDateTime(addTime.getInsertTime()));
//            everyTimeMapper.insert(addTime);
//        }
//    }


    @Test
    public void test1() {
        summeryInfoEveryTimeService.add("hypervisor");
        summeryInfoEveryTimeService.add("vmhost");

    }

}

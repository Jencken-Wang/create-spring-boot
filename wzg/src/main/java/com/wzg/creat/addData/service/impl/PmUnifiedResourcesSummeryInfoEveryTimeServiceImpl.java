package com.wzg.creat.addData.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzg.creat.addData.model.entity.PmUnifiedResourcesSummeryInfoEveryTime;
import com.wzg.creat.addData.model.mapper.PmUnifiedResourcesSummeryInfoEveryTimeMapper;
import com.wzg.creat.addData.service.IPmUnifiedResourcesSummeryInfoEveryTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzg
 * @since 2022-01-19
 */
@Service
public class PmUnifiedResourcesSummeryInfoEveryTimeServiceImpl extends ServiceImpl<PmUnifiedResourcesSummeryInfoEveryTimeMapper, PmUnifiedResourcesSummeryInfoEveryTime> implements IPmUnifiedResourcesSummeryInfoEveryTimeService {

    @Resource
    PmUnifiedResourcesSummeryInfoEveryTimeMapper everyTimeMapper;


    public void add(String type) {
        List<PmUnifiedResourcesSummeryInfoEveryTime> list = everyTimeMapper.selectList(new QueryWrapper<PmUnifiedResourcesSummeryInfoEveryTime>()
                .eq("resource_type", type));

        list.sort(Comparator.comparing(PmUnifiedResourcesSummeryInfoEveryTime::getInsertTime)) ;

        List<PmUnifiedResourcesSummeryInfoEveryTime> listAdd = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                PmUnifiedResourcesSummeryInfoEveryTime everyNow = list.get(i);
                PmUnifiedResourcesSummeryInfoEveryTime everyNext = list.get(i + 1);
//                System.out.println(DateUtil.formatDateTime(everyNow.getInsertTime()));


                Calendar ca = Calendar.getInstance();
                ca.setTime(Date.from(everyNow.getInsertTime().atZone(ZoneId.systemDefault()).toInstant()));
                ca.set(Calendar.SECOND, 0);
                ca.set(Calendar.MILLISECOND, 0);
                long startTime = ca.getTimeInMillis();
                ca.setTime(Date.from(everyNext.getInsertTime().atZone(ZoneId.systemDefault()).toInstant()));
                ca.set(Calendar.SECOND, 0);
                ca.set(Calendar.MILLISECOND, 0);
//                System.out.println(ca.getTime());
                ca.set(Calendar.MINUTE, ca.get(Calendar.MINUTE) / 5 * 5);
//                System.out.println(ca.getTime());

                long endTime = ca.getTimeInMillis();
                long indexTime = startTime;

                Calendar calendar = Calendar.getInstance();
                while (endTime > indexTime + 300000) {
                    calendar.setTimeInMillis(indexTime);
                    calendar.add(Calendar.MINUTE, 5);
                    PmUnifiedResourcesSummeryInfoEveryTime add = JSONUtil.toBean(JSONUtil.toJsonStr(everyNow), PmUnifiedResourcesSummeryInfoEveryTime.class);
                    add.setInsertTime(calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    add.setId(null);
                    listAdd.add(add);
                    indexTime = calendar.getTimeInMillis();
                }

            }
        }
        this.saveBatch(listAdd);
//        for (PmUnifiedResourcesSummeryInfoEveryTime addTime : listAdd) {
//            System.out.println(DateUtil.formatLocalDateTime(addTime.getInsertTime()));
//            everyTimeMapper.insert(addTime);
//        }
    }
}

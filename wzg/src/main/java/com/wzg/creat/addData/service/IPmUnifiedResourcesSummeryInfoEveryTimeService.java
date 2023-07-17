package com.wzg.creat.addData.service;

import com.wzg.creat.addData.model.entity.PmUnifiedResourcesSummeryInfoEveryTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzg
 * @since 2022-01-19
 */
public interface IPmUnifiedResourcesSummeryInfoEveryTimeService extends IService<PmUnifiedResourcesSummeryInfoEveryTime> {


    void add(String type );

}

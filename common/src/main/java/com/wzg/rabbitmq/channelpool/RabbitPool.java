package com.wzg.rabbitmq.channelpool;


import com.rabbitmq.client.Channel;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class RabbitPool {

//    @Autowired
//    private RabbitChannelEvictionPolicy evictionPolicy;

//    private static GenericObjectPool<Channel> channelPool;

    private volatile static HashMap<RabbitChannelFactory, GenericObjectPool> factoryPoolMap = new HashMap<>();

    public GenericObjectPool<Channel> rabbitChannelPool(RabbitChannelFactory channelFactory) throws Exception {

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(4);  // 链接池中最大空闲的连接数,默认为8.
        poolConfig.setMinIdle(3);
        poolConfig.setMaxWaitMillis(60 * 1000); //阻塞后等待時間
        poolConfig.setEvictionPolicyClassName(RabbitChannelEvictionPolicy.class.getName());   //失效时驱逐策略
        poolConfig.setBlockWhenExhausted(true);         //当“连接池”中active数量达到阀值时，即“链接”资源耗尽时，连接池需要采取的手段, 默认为1： 0抛出异常 1阻塞 3强制创建新连接
        poolConfig.setMaxWaitMillis(2 * 1000);         //连接池耗尽时，调用这最大阻塞时间，超时将跑出异常。单位，毫秒数;默认为-1.表示永不超时.
        poolConfig.setTimeBetweenEvictionRunsMillis(5 * 1000);  //“空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1.
        poolConfig.setMinEvictableIdleTimeMillis(5 * 1000); //连接空闲的最小时间，达到此值后空闲连接将可能会被移除。负值(-1)表示不移除。
        poolConfig.setTestOnBorrow(true);  //向调用者输出“链接”资源时，是否检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取。默认为false。建议保持默认值.
        poolConfig.setTestOnReturn(true);   //向连接池“归还”链接时，是否检测“链接”对象的有效性。默认为false。建议保持默认值.
        poolConfig.setTestWhileIdle(false);  //向调用者输出“链接”对象时，是否检测它的空闲超时；默认为false。如果“链接”空闲超时，将会被移除。建议保持默认值.
        poolConfig.setMaxTotal(8);  //链接池中最大连接数,默认为8.

        AbandonedConfig abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        GenericObjectPool genericObjectPool = new GenericObjectPool<>(channelFactory, poolConfig, abandonedConfig);
        genericObjectPool.preparePool(); //提前准备连接
        return genericObjectPool;
    }

    public GenericObjectPool getPool(RabbitChannelFactory channelFactory) throws Exception {

        if (factoryPoolMap.get(channelFactory) == null) {
            synchronized (factoryPoolMap) {
                if (factoryPoolMap.get(channelFactory) == null) {
                    factoryPoolMap.put(channelFactory, rabbitChannelPool(channelFactory));
                }
            }
        }
        return factoryPoolMap.get(channelFactory);
    }

}

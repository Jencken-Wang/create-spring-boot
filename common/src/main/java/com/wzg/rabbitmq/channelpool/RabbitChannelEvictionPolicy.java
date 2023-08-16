package com.wzg.rabbitmq.channelpool;

import com.rabbitmq.client.Channel;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.EvictionConfig;
import org.apache.commons.pool2.impl.EvictionPolicy;
import org.springframework.stereotype.Component;

public class RabbitChannelEvictionPolicy implements EvictionPolicy<Channel> {
    @Override
    public boolean evict(EvictionConfig evictionConfig, PooledObject<Channel> pooledObject, int i) {
        try {
            if (!pooledObject.getObject().isOpen()){
                System.out.println("channel wait time out! channelNumber:" + pooledObject.getObject().getChannelNumber());
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}

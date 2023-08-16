package com.wzg.rabbitmq.channelpool;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.catalina.Host;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/** 
 * @description: 创建rabbitmq channel连接池
 * @author wangzg
 * @date: 2023/7/18 17:54
 */
@Component
public class RabbitChannelFactory extends BasePooledObjectFactory<Channel> {

    @Value("${spring.rabbitmq.addresses}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String passwd;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    private volatile static Connection rabbitConnection;


    Object lock = new Object();

    /**
     * @description: 获取rabbitmq连接
     * @author wangzg
     * @date: 2023/7/18 17:49
     */
    private Connection rabbitConnection() throws IOException, TimeoutException {
        if (rabbitConnection == null || !rabbitConnection.isOpen()) {
            synchronized (lock) {
                if (rabbitConnection == null || !rabbitConnection.isOpen()) {
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setHost(host);
                    factory.setPort(Integer.valueOf(port));
                    factory.setUsername(username);
                    factory.setPassword(passwd);
                    factory.setVirtualHost(virtualHost);
                    rabbitConnection = factory.newConnection();
                }
            }
        }
        return rabbitConnection;
    }
    //如何创建对象
    @Override
    public Channel create() throws Exception {
        Connection connection = rabbitConnection();
        Channel channel = connection.createChannel();
        return channel;
    }

    //定义要返回的对象
    @Override
    public PooledObject<Channel> wrap(Channel channel) {
        return new DefaultPooledObject<>(channel);
    }


    @Override
    public void destroyObject(PooledObject<Channel> p) throws Exception {
        System.out.println("连接被销毁，" + p.getIdleTimeMillis() + " channl:" + p.getObject().getChannelNumber());
        p.getObject().waitForConfirms();
        p.getObject().close();
    }
}

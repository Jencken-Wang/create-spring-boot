package com.wzg.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Value;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {


    public static void main(String[] args) throws IOException, TimeoutException {
        String exchangeName = "wzg_exchange_name";
        String queueName = "wzg_queue_name";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.6.206");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("abcd1234");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();


        /**
         * @description: 创建交换机
         * @param: 1.交换机名字 2.交换机类型direct topic fanout header 3.是否持久化交换机元信息 4.指定交换机没有绑定队列是否删除 5.Map<String, Object>类型用于指定交换机结构化参数 不设置为null
         * @return: void
         * @author wangzg
         * @date: 2023/7/11 1:46
         */
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, null);

        
        
        /** 
         * @description: 创建队列 
         * @param: 1.队列名 2.队列元信息是否持久化 3.队列是否是私有的，私有的仅仅只能由创建他的应用消费 4.队列没有消费者是否删除 5.Map设置信息
         * @return: void 
         * @author wangzg
         * @date: 2023/7/11 1:51
         */
        channel.queueDeclare(queueName, true, false, false, null);

        
        /** 
         * @description: 队列绑定交换机 
         * @param: 1.队列名 2.交换机名 3.路由键，直连模式可以为队列名
         * @return: void 
         * @author wangzg
         * @date: 2023/7/11 1:54
         */
        channel.queueBind(queueName, exchangeName, queueName);


        String message = "Hellow RabbitMQ!";
        /** 
         * @description: 发送消息 
         * @param: 1.交换机名称 2.用于路由的键，直连模式为交换机名称 3.其他参数 4.消息体参数
         * @return: void 
         * @author wangzg
         * @date: 2023/7/11 1:56
         */
        channel.basicPublish(exchangeName, queueName,null, message.getBytes());


        channel.close();
        connection.close();

    }


}

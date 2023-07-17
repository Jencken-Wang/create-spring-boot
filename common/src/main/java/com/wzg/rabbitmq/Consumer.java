package com.wzg.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {


    public static void main(String[] args) throws Exception {
        String exchangeName = "wzg_exchange_name";
        String queueName = "wzg_queue_name";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.6.206");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("abcd1234");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (ConsumerTage, message) ->{
            System.out.println("收到消息" + new String(message.getBody()));
        };

        CancelCallback cancelCallback = ConsumerTage -> {
            System.out.println("消费消息被中断");
        };


        /**
         * @description: 1.队列名称 2. 是否自动ack 3.接受消息后回调函数 4.消费中断后回调函数
         * @param: [args]
         * @return: void
         * @author wangzg
         * @date: 2023/7/11 2:24
         */
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback);


    }
}

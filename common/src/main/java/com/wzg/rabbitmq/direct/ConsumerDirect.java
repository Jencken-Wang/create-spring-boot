package com.wzg.rabbitmq.direct;

import com.rabbitmq.client.*;

public class ConsumerDirect {


    public static void main(String[] args) throws Exception {
        String queue_1 = "wzg_queue_name_1";
        String queue_2 = "wzg_queue_name_2";
        String queue_3 = "wzg_queue_name_3";
        String queue_4 = "wzg_queue_name_4";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.6.206");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("abcd1234");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (ConsumerTage, message) ->{
            System.out.println(  ConsumerTage+"收到消息" + new String(message.getBody()));
        };

        CancelCallback cancelCallback = ConsumerTage -> {
            System.out.println("消费消息被中断");
        };


        channel.basicConsume(queue_1, true, deliverCallback, cancelCallback);
        channel.basicConsume(queue_2, true, deliverCallback, cancelCallback);
//        channel.close();
//        connection.close();
    }
}

package com.wzg.rabbitmq.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerFanout {


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

//        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("收到消息：" + new String(body));
//                channel.basicAck(envelope.getDeliveryTag(), false);
//            }
//        };

//        channel.basicConsume(queue_1, true, deliverCallback, cancelCallback);
//        channel.basicConsume(queue_2, true, deliverCallback, cancelCallback);
//        channel.basicConsume(queue_3, true, deliverCallback, cancelCallback);
        channel.basicConsume(queue_4, false, deliverCallback, cancelCallback);
//        channel.basicConsume(queue_4, defaultConsumer);
//        channel.basicAck();
//        channel.basicAck();
//        channel.close();
//        connection.close();
    }
}

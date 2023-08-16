package com.wzg.creat.rabbit;

import com.rabbitmq.client.Channel;
import com.wzg.creat.user.model.entity.User;
import com.wzg.rabbitmq.RabbitMQCommon;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@RabbitListener(queues = RabbitMQCommon.wzg_queue_name_1)
@Controller
public class RabbitConsumer {


    @RabbitHandler
    public void directReceive(Message message, User user, Channel channel) throws IOException {
        try {
            System.out.println("客户端收到消息 id:" + message.getMessageProperties().getCorrelationId() + "  " + user);
            System.out.println(message);
            if (message != null) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    @RabbitHandler
    public void directReceive(Message message, RabbitTestObj obj, Channel channel) throws IOException {
        try {
            System.out.println("客户端收到消息 id:" + message.getMessageProperties().getHeaders().get("spring_returned_message_correlation") + "  " + obj);
            System.out.println(message);
            if (message != null) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }



}

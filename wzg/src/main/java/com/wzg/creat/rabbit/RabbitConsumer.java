package com.wzg.creat.rabbit;

import com.rabbitmq.client.Channel;
import com.wzg.creat.user.model.entity.User;
import com.wzg.rabbitmq.RabbitMQCommon;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
public class RabbitConsumer {

    @RabbitListener(queues = RabbitMQCommon.wzg_queue_name_1)
    public void directReceive(Message message, User user, Channel channel) throws IOException {
        System.out.println("客户端收到消息：" + user);
        System.out.println(message);
        if (message != null){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }


}

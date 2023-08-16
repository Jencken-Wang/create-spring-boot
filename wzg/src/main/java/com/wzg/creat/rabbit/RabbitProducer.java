package com.wzg.creat.rabbit;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.*;
import com.wzg.creat.user.model.entity.User;
import com.wzg.rabbitmq.RabbitMQCommon;
import com.wzg.rabbitmq.RabbitmqUtil;
import com.wzg.rabbitmq.channelpool.RabbitChannelFactory;
import com.wzg.rabbitmq.channelpool.RabbitPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class RabbitProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

//    @Autowired
//    RabbitmqUtil rabbitmqUtil;

    /**
     * 交换机和队列已经提前在RabbitmqUtil绑定，直接使用即可
     */

    @ResponseBody
    @RequestMapping(value = "/publish",method = RequestMethod.GET)
    public void publish() {
//        rabbitmqUtil.exchangeBindQueue(RabbitMQCommon.wzg_direct_change_name, ExchangeTypes.DIRECT, RabbitMQCommon.wzg_queue_name_1, RabbitMQCommon.dkey1);
        User user = new User();
        user.setUsername("xh");
        user.setGender("female");
        user.setNickname("ah");
        user.setEmail("xh@xx.com");
        user.setPassword("123456");
        user.setDescription(DateUtil.date().toString());
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.fastUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMQCommon.wzg_direct_change_name, RabbitMQCommon.dkey1, user, correlationData);
    }
    @ResponseBody
    @RequestMapping(value = "/publish2",method = RequestMethod.GET)
    public void publish2() {
//        rabbitmqUtil.exchangeBindQueue(RabbitMQCommon.wzg_direct_change_name, ExchangeTypes.DIRECT, RabbitMQCommon.wzg_queue_name_1, RabbitMQCommon.dkey1);
        RabbitTestObj rabbitTestObj = new RabbitTestObj();
        rabbitTestObj.setName("test");
        rabbitTestObj.setMessage("this is test obj");
        rabbitTestObj.setDate(DateUtil.date().toString());
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.fastUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMQCommon.wzg_direct_change_name, RabbitMQCommon.dkey1, rabbitTestObj, correlationData);
    }

    @ResponseBody
    @RequestMapping(value = "/publishtopic",method = RequestMethod.GET)
    public void publishTopic() {
        RabbitTestObj rabbitTestObj = new RabbitTestObj();
        rabbitTestObj.setName("test");
        rabbitTestObj.setMessage("this is test obj");
        rabbitTestObj.setDate(DateUtil.date().toString());
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("wzg110");
        rabbitTemplate.convertAndSend(RabbitMQCommon.wzg_topic_change_name, "wzgkey1.wzgkey2", rabbitTestObj, correlationData);
        rabbitTemplate.convertAndSend(RabbitMQCommon.wzg_topic_change_name, "wzgkey2", rabbitTestObj, correlationData);

    }



    @Autowired
    RabbitPool rabbitPool;
    @Autowired
    RabbitChannelFactory rabbitChannelFactory;
    @ResponseBody
    @GetMapping("/rpublish")
    public void rabbitPoolPublish() throws Exception {
        GenericObjectPool<Channel> pool = rabbitPool.getPool(rabbitChannelFactory);
        Channel channel = pool.borrowObject(10000);

        System.out.println(Thread.currentThread().getName());
        System.out.println("active:"+ pool.getNumActive());
        System.out.println("idle:"+ pool.getNumIdle());
        System.out.println("channelNumer :" + channel.getChannelNumber());
        try {
            RabbitTestObj rabbitTestObj = new RabbitTestObj();
            rabbitTestObj.setName("test");
            rabbitTestObj.setMessage("this is test obj");
            rabbitTestObj.setDate(DateUtil.date().toString());
            rabbitTestObj.setId(Thread.currentThread().getName());
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(Thread.currentThread().getName());
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(pool.getNumActive());
//                System.out.println("channelNumer :" + channel.getChannelNumber());
            channel.confirmSelect();
            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                        System.out.println("confirm交换机已收到消息");
                        System.out.println("confirm消息deliverytag:" + deliveryTag);
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("消息" + deliveryTag + " Nack");
                }
            });


            channel.basicPublish(RabbitMQCommon.wzg_topic_change_name,"wzgkey2", MessageProperties.PERSISTENT_TEXT_PLAIN, JSONUtil.toJsonStr(rabbitTestObj).getBytes());

            pool.returnObject(channel);//发送完消息连接交还连接池
        } catch (Exception e) {
            e.printStackTrace();
            pool.returnObject(channel);
        }
    }

}

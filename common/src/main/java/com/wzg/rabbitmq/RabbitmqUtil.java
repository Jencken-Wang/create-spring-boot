package com.wzg.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitmqUtil {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void initCallback() {
        /**
         * 需要设置spring.rabbitmq.publisher-confirm-type=correlated
         * 消息发broker成功回调：发送到broker的exchange是否正确找到
         * correlationData：当前消息的唯一关联数据(这个是消息的唯一ID)
         * ack：消息是否发送成功
         * cause：失败的原因，成功则返回null
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("confirm交换机已收到消息");

                System.out.println("confirm消息数据:" + correlationData);
                if (correlationData != null) {
                    System.out.println("confirm消息:" + correlationData.getReturnedMessage());
                }
            } else {
                System.out.println("confirm未确认收到消息");
                System.out.println("confirm消息结果：" + ack);
                System.out.println("confirm消息原因：" + cause);
            }
        });
        /**
         * 需要设置spring.rabbitmq.template.mandatory=true或spring.rabbitmq.publisher-returns=true 才会有回调
         * 消息路由回调：从交换器路由到队列是否正确发送
         * message：投递失败消息的详细消息
         * replyCode：回应码
         * replyText：回应信息
         * exchange：当时这个消息发送给的交换器
         * routingKey：当时这个消息发送用的路由键
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("return消息数据：" + message);
            System.out.println("return消息回应码:" + replyCode);
            System.out.println("return消息回应信息:" + replyText);
            System.out.println("return消息交换机：" + exchange);
            System.out.println("return消息路由键：" + routingKey);

        });
    }



    
    /** 
     * @description: 绑定队列和交换机，没有则创建
     * @author wangzg
     * @date: 2023/7/17 15:12
     */
    public void exchangeBindQueue(String exchange, String type, String queue, String key) {
        amqpAdmin.declareExchange(new ExchangeBuilder(exchange, type).durable(true).build());
        amqpAdmin.declareQueue(QueueBuilder.durable(queue).build());
        amqpAdmin.declareBinding(new Binding(queue, Binding.DestinationType.QUEUE, exchange, key, null));

    }

    @Bean
    public void bindNeeded() {
        this.exchangeBindQueue(RabbitMQCommon.wzg_direct_change_name, ExchangeTypes.DIRECT, RabbitMQCommon.wzg_queue_name_1, RabbitMQCommon.dkey1);
        this.exchangeBindQueue(RabbitMQCommon.wzg_topic_change_name, ExchangeTypes.TOPIC, RabbitMQCommon.wzg_queue_name_1, "wzgkey1.#");
        this.exchangeBindQueue(RabbitMQCommon.wzg_topic_change_name, ExchangeTypes.TOPIC, RabbitMQCommon.wzg_queue_name_2, "#.wzgkey2");


    }

}

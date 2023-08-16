import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.wzg.creat.newController;
import com.wzg.creat.user.model.entity.User;
import com.wzg.rabbitmq.RabbitMQCommon;
import com.wzg.rabbitmq.RabbitmqUtil;
import com.wzg.rabbitmq.channelpool.RabbitChannelFactory;
import com.wzg.rabbitmq.channelpool.RabbitPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = newController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RabbitmqTest {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RabbitmqUtil rabbitmqUtil;

    @Test
    public void publish() {

        rabbitmqUtil.exchangeBindQueue(RabbitMQCommon.wzg_direct_change_name, ExchangeTypes.DIRECT, RabbitMQCommon.wzg_queue_name_1, RabbitMQCommon.dkey1);

        User user = new User();
        user.setUsername("xh");
        user.setGender("female");
        user.setNickname("ah");
        user.setEmail("xh@xx.com");
        user.setPassword("123456");

        for (int i = 0; i < 5; i++) {
            user.setDescription(DateUtil.date().toString());
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(String.valueOf(i));
            rabbitTemplate.convertAndSend(RabbitMQCommon.wzg_direct_change_name, RabbitMQCommon.dkey1, user, correlationData);
        }
//        rabbitTemplate.convertAndSend(RabbitMQCommon.wzg_direct_change_name, RabbitMQCommon.dkey1, user);

    }


    @Autowired
    RabbitPool rabbitPool;
    @Autowired
    RabbitChannelFactory rabbitChannelFactory;

    @Test
    public void rabbitPool() throws Exception {

        GenericObjectPool<Channel> pool = rabbitPool.rabbitChannelPool(rabbitChannelFactory);
        Channel channel = pool.borrowObject();
//        Channel channel2 = pool.borrowObject();
        try {
            DeliverCallback deliverCallback = (ConsumerTage, delivery) -> {
                System.out.println("收到消息" + new String(delivery.getBody()));
                long deliveryTage = delivery.getEnvelope().getDeliveryTag();
                channel.basicAck(deliveryTage, false);
            };

            CancelCallback cancelCallback = ConsumerTage -> {
                System.out.println("消费消息被中断");
            };
            System.out.println(pool.getNumActive());
            System.out.println("channelNumer :" + channel.getChannelNumber());
            channel.basicConsume(RabbitMQCommon.wzg_queue_name_2, false, deliverCallback, cancelCallback);
        } catch (Exception e) {
            e.printStackTrace();
            pool.returnObject(channel);
//            pool.returnObject(channel2);
        }
    }



}

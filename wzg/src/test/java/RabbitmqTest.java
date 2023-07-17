import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.wzg.creat.newController;
import com.wzg.creat.user.model.entity.User;
import com.wzg.rabbitmq.RabbitMQCommon;
import com.wzg.rabbitmq.RabbitmqUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
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

}

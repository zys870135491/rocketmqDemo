package com.zys.rocketmqTemplate.mq.producer.delay;

import com.zys.rciketmqdemo.order.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Author: Pine
 * @Date: 2021/12/03/3:11 下午
 * @Desc: 延时消息生产者
 * 延时消息等级分为18个：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
 */
@Component
@Slf4j
public class DelayProducer {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public void delay(OrderStep orderStep){

        SendResult sendResult = rocketMQTemplate.syncSend("pi_delay_topic",
                MessageBuilder.withPayload(orderStep).build(), 3 * 1000, 18);
        log.info("sendResult:"+sendResult);
    }

}

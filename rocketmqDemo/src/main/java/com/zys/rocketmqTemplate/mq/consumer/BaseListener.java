package com.zys.rocketmqTemplate.mq.consumer;

import com.zys.rciketmqdemo.order.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Pine
 * @Date: 2021/12/02/4:29 下午
 * @Desc: 普通消息消费者
 */
@Component
@RocketMQMessageListener(topic = "pi_topic" , consumerGroup = "PP_BASE")
@Slf4j
public class BaseListener implements RocketMQListener<OrderStep> {

    @Override
    public void onMessage(OrderStep orderStep) {
        System.out.println(orderStep);
        log.info("orderStep:{}",orderStep);
    }

}

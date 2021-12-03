package com.zys.rocketmqTemplate.mq.consumer.delay;

import com.zys.rciketmqdemo.order.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Pine
 * @Date: 2021/12/02/4:29 下午
 * @Desc: 延时消息消费者
 */
@Component
@RocketMQMessageListener(topic = "pi_delay_topic" , consumerGroup = "PP_DELAY")
@Slf4j
public class DelayListener implements RocketMQListener<OrderStep> {

    @Override
    public void onMessage(OrderStep orderStep) {
        log.info("DelayListener orderStep:{}",orderStep);
    }

}
